package com.testerkit.uia.servers.socket;

import com.testerkit.uia.requests.http.IHttpRequest;
import com.testerkit.uia.requests.http.IHttpResponse;
import com.testerkit.uia.requests.socket.ISocketRequest;
import com.testerkit.uia.requests.socket.ISocketResponse;
import com.testerkit.uia.servers.IServlet;

public interface ISocketServlet extends IServlet {
    void handleSocketRequest(ISocketRequest request, ISocketResponse response) throws Exception;
}
