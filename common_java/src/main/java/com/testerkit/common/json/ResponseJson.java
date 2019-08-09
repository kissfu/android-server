package com.testerkit.common.json;

public class ResponseJson {
    private  int status = -1;
    private String event;
    private String key="";
    private  String value = "";
    private  String sessionId ;

    public ResponseJson() {
    }
    public ResponseJson(int status, Object value, String sessionId) {
        this.status = status;
        this.value = value.toString();
        this.sessionId = sessionId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


    @Override
    public String toString() {
        return "ResponseJson{" +
                "status=" + status +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
