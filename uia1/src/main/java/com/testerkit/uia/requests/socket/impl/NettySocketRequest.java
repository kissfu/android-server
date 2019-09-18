package com.testerkit.uia.requests.socket.impl;

import com.testerkit.common.json.StepJson;
import com.testerkit.uia.requests.socket.FullSocketRequest;
import com.testerkit.uia.requests.socket.ISocketRequest;

import java.util.Map;

public class NettySocketRequest implements ISocketRequest {
    private FullSocketRequest request;

    public NettySocketRequest(FullSocketRequest request) {
        this.request = request;
    }


    @Override
    public String uri() {
        return request.uri();
    }

    @Override
    public String body() {
        return request.body();
    }

    @Override
    public Map<String, Object> data() {
        return null;
    }

    @Override
    public StepJson getStepInfo() {
        return request.step;
    }

    @Override
    public String getRawMsg() {
        return request.rawMsg;
    }
}
