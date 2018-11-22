package com.testerkit.uia.model.serach;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by able on 2018/9/28.
 */

public class StepInfo {
    private String action;

    private String rule;

    private KeyInfo key;

    private NodeInfo node;

    private ScrollInfo scroll;

    private List<By> by;

    private List<PointInfo> points;


    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return this.action;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getRule() {
        return this.rule;
    }

    public void setNode(NodeInfo node) {
        this.node = node;
    }

    public NodeInfo getNode() {
        return this.node;
    }

    public KeyInfo getKey() {
        return key;
    }

    public void setKey(KeyInfo key) {
        this.key = key;
    }

    public void setScroll(ScrollInfo scroll) {
        this.scroll = scroll;
    }

    public ScrollInfo getScroll() {
        return this.scroll;
    }

    public void setBy(List<By> by) {
        this.by = by;
    }

    public List<By> getBy() {
        return this.by;
    }

    public List<PointInfo> getPoints() {
        return points;
    }

    public void setPoints(List<PointInfo> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return String.format("key=%s",
                key == null ? "" : key.toString());
    }

    public String uri() {
        return this.getAction()+"/"+this.getRule();
    }

    public void parse(String msg){
        JsonObject js = new JsonParser().parse(msg).getAsJsonObject();

        this.setAction(js.get("action").getAsString());

        this.setRule(js.get("rule").getAsString());

        // 1. 创建Gson对象
        Gson gson = new Gson();

        if(js.has("points")){
            List<PointInfo> list=  gson.fromJson(js.get("points"),new TypeToken<List<PointInfo>>(){}.getType());
            this.setPoints(list);
        }
        if(js.has("node")){
            this.setNode(gson.fromJson(js.get("node"),NodeInfo.class));
        }
        if(js.has("scroll")){
            this.setScroll(gson.fromJson(js.get("scroll"),ScrollInfo.class));
        }
        if(js.has("key")){
            this.setKey(gson.fromJson(js.get("key"),KeyInfo.class));
        }
        if(js.has("by")){

            JsonArray arr = js.getAsJsonArray("by");
            List<By> byList = new ArrayList<By>();


            for (JsonElement ele:arr) {
                JsonObject obj = ele.getAsJsonObject();
                if(obj.has("name")){
                    byList.add(gson.fromJson(obj,By.ByName.class));
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


            this.setBy(byList);
        }

    }
}
