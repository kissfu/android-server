package com.testerkit.uia.model.serach;

import android.graphics.Rect;

/**
 * Created by able on 2018/9/28.
 *  "node":{
 *     "type":"uia",
 *     "index":"3",
 *     "bounds":{}
 *   },
 */

public class NodeInfo {
    private String type;
    private int index;
    private Rect bounds;


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

    public Rect getBounds() {
        return bounds;
    }

    public void setBounds(Rect bounds) {
        this.bounds = bounds;
    }
}
