package com.testerkit.uia.requests.socket;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.testerkit.uia.model.serach.KeyInfo;
import com.testerkit.uia.model.serach.NodeInfo;
import com.testerkit.uia.model.serach.PointInfo;
import com.testerkit.uia.model.serach.ScrollInfo;
import com.testerkit.uia.model.serach.StepInfo;
import com.testerkit.uia.model.serach.By;

import java.util.ArrayList;
import java.util.List;


/**
 * 解析请求的内容
 */
public class FullSocketRequest {



    public StepInfo step;


    public FullSocketRequest(String msg) {
        step = new StepInfo();
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
