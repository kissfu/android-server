package com.testerkit.common.json;

import com.testerkit.common.utils.GsonUtil;

import java.util.List;

/**
 * Created by able on 2018/9/28.
 */

public class StepJson {
    private String action;

    private String rule;

    private KeyJson key;

    private NodeJson node;

    private ScrollJson scroll;

    //private List<ByBase> by;

    private List<PointJson> points;

    private ConditionJson condition;


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

    public void setNode(NodeJson node) {
        this.node = node;
    }

    public NodeJson getNode() {
        return this.node;
    }

    public KeyJson getKey() {
        return key;
    }

    public void setKey(KeyJson key) {
        this.key = key;
    }

    public void setScroll(ScrollJson scroll) {
        this.scroll = scroll;
    }

    public ScrollJson getScroll() {
        return this.scroll;
    }

//    public void setBy(List<ByBase> by) {
//        this.by = by;
//    }
//
//    public List<ByBase> getBy() {
//        return this.by;
//    }

    public List<PointJson> getPoints() {
        return points;
    }

    public void setPoints(List<PointJson> points) {
        this.points = points;
    }



    public String uri() {
        return this.getAction()+"/"+this.getRule();
    }

    public ConditionJson getCondition() {
        return condition;
    }

    public void setCondition(ConditionJson condition) {
        this.condition = condition;
    }

    public static StepJson newParse(String msg){
        try {
            return GsonUtil.toBean(msg,StepJson.class);
        }catch (Exception e){
            e.printStackTrace();
        }
      return null;

//        JsonObject js = new JsonParser().parse(msg).getAsJsonObject();
//
//        this.setAction(js.get("action").getAsString());
//
//        this.setRule(js.get("rule").getAsString());
//
//        // 1. 创建Gson对象
//        Gson gson = new Gson();
//
//        if(js.has("points")){
//            List<PointJson> list=  gson.fromJson(js.get("points"),new TypeToken<List<PointJson>>(){}.getType());
//            this.setPoints(list);
//        }
//        if(js.has("node")){
//            this.setNode(gson.fromJson(js.get("node"),NodeJson.class));
//        }
//        if(js.has("scroll")){
//            this.setScroll(gson.fromJson(js.get("scroll"),ScrollJson.class));
//        }
//        if(js.has("key")){
//            this.setKey(gson.fromJson(js.get("key"),KeyJson.class));
//        }
//        if(js.has("by")){
//
//            JsonArray arr = js.getAsJsonArray("by");
//            List<ByBase> byList = new ArrayList<ByBase>();
//
//
//            for (JsonElement ele:arr) {
//                JsonObject obj = ele.getAsJsonObject();
//                if(obj.has("name")){
//                    byList.add(gson.fromJson(obj,ByName.class));
//                }else if(obj.has("text")){
//                    byList.add(gson.fromJson(obj,ByText.class));
//                }else if(obj.has("class")){
//                    byList.add(gson.fromJson(obj,ByClazz.class));
//                }else if(obj.has("packageName")){
//                    byList.add(gson.fromJson(obj,ByPackageName.class));
//                }else if(obj.has("xpathes")){
//                    byList.add(gson.fromJson(obj,ByXPath.class));
//                }
//            }
//
//            this.setBy(byList);
//        }
    }


    @Override
    public String toString() {
        return String.format("key=%s",
                key == null ? "" : key.toString());
    }
}
