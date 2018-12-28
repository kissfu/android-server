package com.testerkit.uia.requests.socket;

import com.testerkit.uia.requests.IResponse;

import java.nio.charset.Charset;

public interface ISocketResponse extends IResponse {

    byte[] getBytesWithLen();
}
