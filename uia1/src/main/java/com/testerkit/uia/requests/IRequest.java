package com.testerkit.uia.requests;

import java.util.Map;

/**
 * Created by able on 2018/9/13.
 */

public interface IRequest {
    /**
     * Returns the request URI.
     */
    String uri();

    /**
     * Returns the full request body.
     */
    String body();

    /**
     * Returns additional data appended to the request.
     */
    Map<String, Object> data();
}
