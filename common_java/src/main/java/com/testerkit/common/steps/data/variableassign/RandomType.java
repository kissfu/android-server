package com.testerkit.common.steps.data.variableassign;

/**
 * @atuthor able
 */
public enum RandomType {
    IDENTITY("身份证"),
    NAME("姓名"),
    EMAIL("Email"),
    ADDRESS("地址"),
    PHONE_NUMBER("手机号码"),
    BANK_CARD_NUMBER("银行卡号");

    private String message;

    RandomType(String message){
        this.message = message;
    }
}
