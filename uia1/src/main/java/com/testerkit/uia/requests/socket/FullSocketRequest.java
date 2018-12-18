package com.testerkit.uia.requests.socket;


import com.testerkit.common.json.StepJson;




/**
 * 解析请求的内容
 */
public class FullSocketRequest {



    public StepJson step;


    public FullSocketRequest(String msg) {
        step = new StepJson();
        step.parse(msg);
    }


    public String uri() {
        return step.uri();
    }


    public String body() {
        return step.toString();
    }

    public static void main(String[] args) {
        String json = "{\n" +
                "  \"action\":\"press\",\n" +
                "  \"rule\":\"keycode\",\n" +
                "  \"key\":{\n" +
                "    \"keyName\":\"HOME\",\n" +
                "    \"keyCode\":3,\n" +
                "    \"metaState\":0\n"+
                "  }\n" +
                "}";
        FullSocketRequest socketRequest = new FullSocketRequest(json);
    }

}
