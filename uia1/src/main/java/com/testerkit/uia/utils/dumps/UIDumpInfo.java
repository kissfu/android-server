package com.testerkit.uia.utils.dumps;

import java.util.ArrayList;

public class UIDumpInfo {


    private String uiXml;

    // 所有node的基本信息列表
    private ArrayList<MyNode> nodes = new ArrayList<MyNode>();



     public void addNode(MyNode node){
         nodes.add(node);
     }


     //region getter setter

    public String getUiXml() {
        return uiXml;
    }

    public void setUiXml(String uiXml) {
        this.uiXml = uiXml;
    }

    public ArrayList<MyNode> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<MyNode> nodes) {
        this.nodes = nodes;
    }


    //endregion


}
