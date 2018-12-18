package com.testerkit.common.model;

import java.util.ArrayList;

public class UIDumpInfo {


    private String uiXml;

    // 所有node的基本信息列表
    private ArrayList<NodeInfo> nodes = new ArrayList<NodeInfo>();



     public void addNode(NodeInfo node){
         nodes.add(node);
     }


     //region getter setter

    public String getUiXml() {
        return uiXml;
    }

    public void setUiXml(String uiXml) {
        this.uiXml = uiXml;
    }

    public ArrayList<NodeInfo> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<NodeInfo> nodes) {
        this.nodes = nodes;
    }


    //endregion




}
