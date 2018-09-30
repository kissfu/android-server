package com.testerkit.uia.model;

import com.testerkit.uia.model.serach.By;

import java.util.List;

/**
 * Created by able on 2018/9/28.
 */

public class StepInfo {
    private String action;

    private String rule;

    private String timeOut;

    private NodeInfo node;

    private ScrollInfo scroll;

    private List<By> by ;



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
    public void setTimeOut(String timeOut){
        this.timeOut = timeOut;
    }
    public String getTimeOut(){
        return this.timeOut;
    }
    public void setNode(NodeInfo node){
        this.node = node;
    }
    public NodeInfo getNode(){
        return this.node;
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

}
