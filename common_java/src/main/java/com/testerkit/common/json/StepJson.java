package com.testerkit.common.json;

import com.testerkit.common.enums.StepAction;
import com.testerkit.common.log.Logger;
import com.testerkit.common.utils.GsonUtil;
import com.testerkit.common.utils.StringUtil;

import java.util.ArrayList;
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
            Logger.error("StepJson newParse ",e);
        }
      return null;
    }

    public String toDescriptionn() {
        List<String> list = new ArrayList<String>();
        if(scroll != null){
            list.add("超时:"+scroll.getTimeout()+"毫秒");
        }
        if(this.action.equals(StepAction.FIND.getAction())){
            list.add(condition.toDescription());
        }
        if(this.action.equals(StepAction.PRESS.getAction())){
            list.add(key.toDescription());
        }
        return StringUtil.join(list.toArray(), ",");
    }
    @Override
    public String toString() {
        return "StepJson{" +
                "action='" + action + '\'' +
                ", rule='" + rule + '\'' +
                ", key=" + key +
                ", node=" + node +
                ", scroll=" + scroll +
                ", points=" + points +
                ", condition=" + condition +
                '}';
    }
}
