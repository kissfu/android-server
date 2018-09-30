package com.testerkit.uia.servers.http;

import com.testerkit.uia.requests.http.IHttpRequest;
import com.testerkit.uia.requests.http.IHttpResponse;
import com.testerkit.uia.servers.IServlet;

public interface IHttpServlet extends IServlet {
    void handleHttpRequest(IHttpRequest IHttpRequest, IHttpResponse httpResponse) throws Exception;
}
