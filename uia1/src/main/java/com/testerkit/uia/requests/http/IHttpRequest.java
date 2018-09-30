package com.testerkit.uia.requests.http;

import com.testerkit.uia.requests.IRequest;

import java.util.Map;

public interface IHttpRequest extends IRequest {
    /**
     * Returns "GET", "POST", "PUT" or "DELETE".
     */
    String method();

    /**
     * Returns the request URI.
     */
    String uri();

    /**
     * Returns the full request body.
     */
    String body();

    /**
     * Gets the value of a given header.
     */
    String header(String name);
}
