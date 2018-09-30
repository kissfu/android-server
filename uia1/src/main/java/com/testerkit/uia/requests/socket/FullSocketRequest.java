package com.testerkit.uia.requests.socket;

import com.testerkit.uia.model.StepInfo;


/**
 * 解析请求的内容
 */
public class FullSocketRequest {


    public StepInfo step;


    public void parseStep(String str){
        step = new StepInfo();
    }

    public String uri() {
        return step.getAction();
    }


    public String body() {
        return step.toString();
    }

}
