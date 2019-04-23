package com.testerkit.uia.requests.http;


import android.util.Log;

import com.testerkit.common.constants.ConstantResult;
import com.testerkit.common.enums.WDStatus;
import com.testerkit.common.json.ResponseJson;
import com.testerkit.common.log.Logger;
import com.testerkit.common.utils.GsonUtil;


public class AppiumResponse {
    private final ResponseJson response;

    public AppiumResponse(String sessionId, WDStatus status, Object value) {
        response = new ResponseJson(status.code(),value,sessionId);
    }
    public AppiumResponse(String sessionId, WDStatus status, Object value,String key) {
        this(sessionId, status, value);
        this.response.setKey(key);
    }
    public AppiumResponse(String sessionId, WDStatus status, Throwable throwable) {
        response = new ResponseJson(status.code(),Log.getStackTraceString(throwable),sessionId);
        response.setKey(ConstantResult.EXCEPTION_DEVICE);
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
            return GsonUtil.gsonString(response);
        } catch (Exception e) {
            response.setStatus(WDStatus.JSON_DECODER_ERROR.code());
            response.setValue(e.getMessage());
            Logger.error("Unable to create JSON Object:", e);
        }
        return GsonUtil.gsonString(response);
    }

    public int getStatus() {
        return response.getStatus();
    }

    public Object getValue() {
        return response.getValue();
    }


}

