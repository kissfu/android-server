package com.testerkit.common.steps.data.sms;

import com.testerkit.common.json.StepJson;

/**
 * @atuthor able
 */
public class SdSmsVerificationCode  extends StepJson {
    private String regex;

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
}
