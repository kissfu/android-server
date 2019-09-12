package com.testerkit.common.enums;

/**
 * Created by WZJ
 * Time       2019/9/9.
 */
public enum EventMsgType {
    NEED_RETURN(0),   //需要返回
    NO_RETURN(1); //无需返回

    private final int value;

    EventMsgType(int type) {
        this.value = type;
    }

    public int getType() {
        return value;
    }

    public static EventMsgType getRule(int value) {
        for (EventMsgType v : EventMsgType.values()) {
            if (v.getType() == value) {
                return v;
            }
        }
        return NEED_RETURN;
    }
}
