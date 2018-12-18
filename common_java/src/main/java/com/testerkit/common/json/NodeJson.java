package com.testerkit.common.json;


import com.testerkit.common.model.RectInfo;

/**
 * Created by able on 2018/9/28.
 *  "node":{
 *     "type":"uia",
 *     "index":"3",
 *     "bounds":{}
 *   },
 */

public class NodeJson {
    private String type;
    private int index;
    private RectInfo bounds;
    private String text;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public RectInfo getBounds() {
        return bounds;
    }

    public void setBounds(RectInfo bounds) {
        this.bounds = bounds;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
