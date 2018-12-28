package com.testerkit.uia.requests.socket.impl;

import com.testerkit.uia.requests.IResponse;
import com.testerkit.uia.requests.socket.FullSocketResponse;
import com.testerkit.uia.requests.socket.ISocketResponse;
import com.testerkit.uia.utils.ByteUtil;

import java.nio.charset.Charset;

import io.netty.util.CharsetUtil;

public class NettySocketResponse implements ISocketResponse {

    private final FullSocketResponse response;
//    private final String CONTENT_TYPE = "Content-Type";
//    private final String CONTENT_ENCODING = "Content-Encoding";
    private final String CONTENT_LENGTH = "Content-Length";
//    private final String LOCATION = "location";
    private boolean closed;
    private Charset charset = CharsetUtil.UTF_8;


    public NettySocketResponse(FullSocketResponse response) {
        this.response = response;
    }

    public IResponse setStatus(int status) {
        response.setStatus(status);
        return this;
    }


    public IResponse setContent(byte[] data) {
        response.setContent(data);
        response.setContent(new String(data,charset));
        return this;
    }

    public IResponse setContent(String message) {
        response.setContent(message);
        response.setContent(message.getBytes(charset));
        return this;
    }

    @Override
    public byte[] getBytesWithLen(){
        byte[] bytes = response.getBytes();

        byte[] numBytes = ByteUtil.intToByteArray(bytes.length);

        byte[] totalBytes = ByteUtil.byteMerger(numBytes,bytes);

        return totalBytes;
    }


    @Override
    public void end() {
        closed = true;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public IResponse setEncoding(Charset charset) {
        this.charset = charset;
        return this;
    }
}
