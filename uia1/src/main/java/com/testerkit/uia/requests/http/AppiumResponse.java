package com.testerkit.uia.requests.http;


import android.util.Log;

import com.testerkit.common.enums.WDStatus;
import com.testerkit.common.json.ResponseJson;
import com.testerkit.common.log.Logger;
import com.testerkit.common.utils.GsonUtil;

import org.json.JSONException;
import org.json.JSONObject;


public class AppiumResponse {
    private final ResponseJson responseJson;

    public AppiumResponse(String sessionId, WDStatus status, Object value) {
        responseJson = new ResponseJson(status.code(),value,sessionId);
//        this.sessionId = sessionId;
//        this.status = status.code();
//        this.value = value;
    }

    public AppiumResponse(String sessionId, WDStatus status, Throwable throwable) {
        responseJson = new ResponseJson(status.code(),Log.getStackTraceString(throwable),sessionId);
//        this.sessionId = sessionId;
//        this.status = status.code();
//        this.value = Log.getStackTraceString(throwable);
    }

    public AppiumResponse(String sessionId, Object value) {
        this(sessionId, WDStatus.SUCCESS, value);
    }

    public AppiumResponse(String sessionId, WDStatus status) {
        this(sessionId, status, status.message());
    }

    public static AppiumResponse forCatchAllError(String sessionId, Throwable e) {
        return new AppiumResponse(sessionId, WDStatus.UNKNOWN_ERROR, e);
    }

    public String render() {

        try {
            return GsonUtil.gsonString(responseJson);
        } catch (Exception e) {
            responseJson.setStatus(WDStatus.JSON_DECODER_ERROR.code());
            responseJson.setValue(e.getMessage());
            Logger.error("Unable to create JSON Object:", e);
        }
        return GsonUtil.gsonString(responseJson);
    }

    public int getStatus() {
        return responseJson.getStatus();
    }

    public Object getValue() {
        return responseJson.getValue();
    }


}

