package com.testerkit.uia.servers.socket;

import com.testerkit.common.constants.ConstantStep;
import com.testerkit.uia.handlers.app.AppList;
import com.testerkit.uia.handlers.dump.SourceClass;
import com.testerkit.uia.handlers.dump.SourceNode;
import com.testerkit.uia.handlers.find.FindAndClick;
import com.testerkit.uia.handlers.input.InputDefault;
import com.testerkit.uia.handlers.key.PressKeyCode;
import com.testerkit.uia.handlers.key.PressKeyName;
import com.testerkit.uia.handlers.request.BaseRequestHandler;
import com.testerkit.uia.handlers.touch.TouchDown;
import com.testerkit.uia.handlers.touch.TouchLongClick;
import com.testerkit.uia.handlers.touch.TouchMove;
import com.testerkit.uia.handlers.touch.TouchUp;
import com.testerkit.uia.requests.http.AppiumResponse;
import com.testerkit.uia.requests.socket.ISocketRequest;
import com.testerkit.uia.requests.socket.ISocketResponse;
import com.testerkit.uia.servers.HttpStatusCode;


import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class NettyServlet implements ISocketServlet {


    protected static ConcurrentMap<String, BaseRequestHandler> getHandler = new ConcurrentHashMap<>();

    private ConcurrentMap<String, String[]> mapperUrlSectionsCache = new ConcurrentHashMap<>();
    public NettyServlet(){
        init();
    }

    //region init request handler
    private final static String baseUri = "";//"/wd/hub/session/:sessionId/";

    private void init() {
        registerGetHandler();
    }
    private void  registerGetHandler(){
        register(getHandler, new FindAndClick(baseUri+ConstantStep.FIND_CLICK));

        register(getHandler, new SourceClass(baseUri+ConstantStep.SOURCE_CLASS));
        register(getHandler, new SourceNode(baseUri+ConstantStep.SOURCE_NODE));

        register(getHandler, new InputDefault(baseUri+ConstantStep.INPUT_DEFAULT));

        register(getHandler, new PressKeyCode(baseUri+ConstantStep.PRESS_KEY));
        register(getHandler, new PressKeyName(baseUri+ConstantStep.PRESS_KEY_NAME));


        register(getHandler, new TouchDown(baseUri+ConstantStep.TOUCH_DOWN));
        register(getHandler, new TouchUp(baseUri+ConstantStep.TOUCH_UP));
        register(getHandler, new TouchMove(baseUri+ConstantStep.TOUCH_MOVE));
        register(getHandler, new TouchLongClick(baseUri+ConstantStep.TOUCH_CLICK));

        register(getHandler, new AppList(baseUri+ConstantStep.APP_LIST));
    }
    protected void register(Map<String, BaseRequestHandler> registerOn, BaseRequestHandler handler) {
        registerOn.put(handler.getMappedUri(), handler);
    }



    //endregion


    //region matcher request handler

    protected BaseRequestHandler findMatcher(ISocketRequest request, Map<String, BaseRequestHandler> handler) {
        String[] urlToMatchSections = getRequestUrlSections(request.uri());
        for (Map.Entry<String, ? extends BaseRequestHandler> entry : handler.entrySet()) {
            String[] mapperUrlSections = getMapperUrlSectionsCached(entry.getKey());
            if (isFor(mapperUrlSections, urlToMatchSections)) {
                return entry.getValue();
            }
        }
        return null;
    }
    private String[] getRequestUrlSections(String urlToMatch) {
        if (urlToMatch == null) {
            return null;
        }
        int qPos = urlToMatch.indexOf('?');
        if (qPos != -1) {
            urlToMatch = urlToMatch.substring(0, urlToMatch.indexOf("?"));
        }
        return urlToMatch.split("/");
    }

    private String[] getMapperUrlSectionsCached(String mapperUrl) {
        String[] sections = mapperUrlSectionsCache.get(mapperUrl);
        if (sections == null) {
            sections = mapperUrl.split("/");
            for (int i = 0; i < sections.length; i++) {
                String section = sections[i];
                // To work around a but in Selenium Grid 2.31.0.
                int qPos = section.indexOf('?');
                if (qPos != -1) {
                    sections[i] = section.substring(0, qPos);
                }
            }
            mapperUrlSectionsCache.put(mapperUrl, sections);
        }
        return sections;
    }

    protected boolean isFor(String[] mapperUrlSections, String[] urlToMatchSections) {
        if (urlToMatchSections == null) {
            return mapperUrlSections.length == 0;
        }
        if (mapperUrlSections.length != urlToMatchSections.length) {
            return false;
        }
        for (int i = 0; i < mapperUrlSections.length; i++) {
            if (!(mapperUrlSections[i].startsWith(":") || mapperUrlSections[i].equals(urlToMatchSections[i]))) {
                return false;
            }
        }
        return true;
    }
    //endregion

    //region response requert handler

    protected void handleResponse(ISocketRequest request, ISocketResponse response, AppiumResponse result) {
        if (result != null) {
            String resultString = result.render();
            //response.setContentType("application/json");
            response.setEncoding(Charset.forName("UTF-8"));
            response.setContent(resultString);
            response.setStatus(result.getStatus());
//            try {
//                //new JSONObject(resultString).getInt("status")
//
//                if (result.getStatus() == 0) {
//                    response.setStatus(HttpStatusCode.OK.getStatusCode());
//                } else {
//                    response.setStatus(HttpStatusCode.INTERNAL_SERVER_ERROR.getStatusCode());
//                }
//            } catch (Exception e) {
//                response.setStatus(HttpStatusCode.INTERNAL_SERVER_ERROR.getStatusCode());
//            }
        }
        response.end();
    }

    //endregion


    @Override
    public void handleSocketRequest(ISocketRequest request, ISocketResponse response) throws Exception {

        BaseRequestHandler handler = findMatcher(request,getHandler);

        if (handler == null) {
            response.setStatus(HttpStatusCode.NOT_FOUND.getStatusCode()).end();
            return;
        }

        AppiumResponse result = handler.handle(request);
        handleResponse(request, response, result);
    }


}
