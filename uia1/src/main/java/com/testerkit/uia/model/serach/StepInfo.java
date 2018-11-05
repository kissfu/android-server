package com.testerkit.uia.model.serach;

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

    private List<By> by ;

    private List<PointInfo> points;



    public void setAction(String action){
        this.action = action;
    }
    public String getAction(){
        return this.action;
    }
    public void setRule(String rule){
        this.rule = rule;
    }
    public String getRule(){
        return this.rule;
    }
    public void setNode(NodeInfo node){
        this.node = node;
    }
    public NodeInfo getNode(){
        return this.node;
    }

    public KeyInfo getKey() {
        return key;
    }

    public void setKey(KeyInfo key) {
        this.key = key;
    }

    public void setScroll(ScrollInfo scroll){
        this.scroll = scroll;
    }
    public ScrollInfo getScroll(){
        return this.scroll;
    }
    public void setBy(List<By> by){
        this.by = by;
    }
    public List<By> getBy(){
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
        return String.format("key=%s",key.toString());
    }
}
