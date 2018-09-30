package com.testerkit.uia.requests.http;

import com.testerkit.uia.requests.IResponse;

import java.nio.charset.Charset;

public interface IHttpResponse extends IResponse {

    IHttpResponse setStatus(int status);

    IHttpResponse setContentType(String mimeType);

    IHttpResponse setContent(byte[] data);

    IHttpResponse setContent(String message);

    IHttpResponse setEncoding(Charset charset);

    IHttpResponse sendRedirect(String to);

    IHttpResponse sendTemporaryRedirect(String to);

    void end();

    boolean isClosed();
}
