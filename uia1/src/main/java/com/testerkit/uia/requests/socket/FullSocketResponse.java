package com.testerkit.uia.requests.socket;


import com.testerkit.uia.servers.HttpStatusCode;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;

/**
 * 1、组织返回的内容
 */
public class FullSocketResponse  {


    private int status;
    private String content;
    private byte[] bytes;



    public void setStatus(int status) {
        this.status = status;
    }


    public void setContent(String message) {
        this.content = message;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setContent(byte[] data) {
        this.bytes = data;
    }

}
