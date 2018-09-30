package com.testerkit.uia.requests.socket.impl;

import com.testerkit.uia.model.StepInfo;
import com.testerkit.uia.requests.http.IHttpRequest;
import com.testerkit.uia.requests.socket.FullSocketRequest;
import com.testerkit.uia.requests.socket.ISocketRequest;

import java.util.HashMap;
import java.util.Map;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.CharsetUtil;

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

}
