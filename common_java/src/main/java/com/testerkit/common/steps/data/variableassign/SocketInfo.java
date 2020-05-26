package com.testerkit.common.steps.data.variableassign;

/**
 * @atuthor able
 */
public class SocketInfo {
    private String ip;
    private int port;
    private int timeout;
    private RequestType requestType;
    private String requestBody;
    private String responseBody;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public String toString() {
        return "SocketInfo{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", timeout=" + timeout +
                ", requestType=" + requestType +
                ", requestBody='" + requestBody + '\'' +
                ", responseBody='" + responseBody + '\'' +
                '}';
    }
}
