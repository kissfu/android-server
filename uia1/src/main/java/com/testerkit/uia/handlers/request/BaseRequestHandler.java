package com.testerkit.uia.handlers.request;

import com.testerkit.common.json.StepJson;
import com.testerkit.uia.requests.IRequest;
import com.testerkit.uia.requests.http.AppiumResponse;
import com.testerkit.uia.requests.http.IHttpRequest;
import com.testerkit.uia.requests.socket.ISocketRequest;
import com.testerkit.uia.servers.http.AppiumServlet;
import com.testerkit.uia.utils.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class BaseRequestHandler {

    private final String mappedUri;

    public BaseRequestHandler(String mappedUri) {
        this.mappedUri = mappedUri;
    }

    public String getMappedUri() {
        return mappedUri;
    }

    public String getElementId(IHttpRequest request) {
        return (String) request.data().get(AppiumServlet.ELEMENT_ID_KEY);

    }

    public String getNameAttribute(IHttpRequest request) {

        return (String) request.data().get(AppiumServlet.NAME_ID_KEY);
    }

    public JSONObject getPayload(IHttpRequest request) throws JSONException {
        String json = request.body();
        Logger.debug("payload: " + json);
        if (json != null && !json.isEmpty()) {
            return new JSONObject(json);
        }
        return new JSONObject();
    }

    public StepJson getStep(IRequest request){
        if(request instanceof  ISocketRequest) {
            return ((ISocketRequest)request).getStepInfo();
        }
        return null;
    }

    public Map<String, Object> getPayload(IHttpRequest request, String jsonKey) throws JSONException {
        JSONObject payload = getPayload(request);
        if (jsonKey != null) {
            payload = payload.getJSONObject(jsonKey);
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = payload.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = payload.get(key);
            map.put(key, value);
        }
        return map;
    }

    public String getSessionId(IRequest request) {

        if(request.data() == null){
            return "NO_SESSION_ID";
        }
        return (String) request.data().get(AppiumServlet.SESSION_ID_KEY);
    }

    public abstract AppiumResponse handle(IRequest request);

    protected AppiumResponse safeHandle(IRequest request) throws Exception {
        return handle(request);
    }
}
