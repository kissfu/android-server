package com.testerkit.uia.requests;

import com.testerkit.uia.requests.socket.ISocketResponse;

import java.nio.charset.Charset;

/**
 * Created by able on 2018/9/29.
 */

public interface IResponse {

    IResponse setEncoding(Charset charset);


    IResponse setStatus(int status);

    IResponse setContent(byte[] data);

    IResponse setContent(String message);

    void end();

    boolean isClosed();
}
