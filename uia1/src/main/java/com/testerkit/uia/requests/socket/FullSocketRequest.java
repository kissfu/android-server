package com.testerkit.uia.requests.socket;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.testerkit.uia.model.serach.NodeInfo;
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

        JsonObject js = new  JsonParser().parse(msg).getAsJsonObject();

        step = new StepInfo();

        step.setAction(js.get("action").getAsString());

        step.setRule(js.get("rule").getAsString());

        // 1. 创建Gson对象
        Gson gson = new Gson();
        if(js.has("node")){
            step.setNode(gson.fromJson(msg,NodeInfo.class));
        }
        if(js.has("scroll")){
            step.setScroll(gson.fromJson(msg,ScrollInfo.class));
        }
        if(js.has("by")){

            JsonArray arr = js.getAsJsonArray("by");
            List<By> byList = new ArrayList<By>();


            for (JsonElement ele:arr) {
                JsonObject obj = ele.getAsJsonObject();
                if(obj.has("id")){
                    byList.add(gson.fromJson(obj,By.ById.class));
                }else if(obj.has("text")){
                    byList.add(gson.fromJson(obj,By.ByText.class));
                }else if(obj.has("class")){
                    byList.add(gson.fromJson(obj,By.ByClass.class));
                }else if(obj.has("packageName")){
                    byList.add(gson.fromJson(obj,By.ByPackageName.class));
                }else if(obj.has("xpath")){
                    byList.add(gson.fromJson(obj,By.ByXPath.class));
                }
            }


            step.setBy(byList);
        }
    }


    public String uri() {
        return step.getAction();
    }


    public String body() {
        return step.toString();
    }

    public static void main(String[] args) {
        FullSocketRequest socketRequest = new FullSocketRequest("");
    }

}
