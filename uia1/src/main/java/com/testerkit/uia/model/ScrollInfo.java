package com.testerkit.uia.model;

/**
 * Created by able on 2018/9/28.
 */

public class ScrollInfo {
    private int times;

    private String toCenter;

    private String direction;

    public void setTimes(int times){
        this.times = times;
    }
    public int getTimes(){
        return this.times;
    }
    public void setToCenter(String toCenter){
        this.toCenter = toCenter;
    }
    public String getToCenter(){
        return this.toCenter;
    }
    public void setDirection(String direction){
        this.direction = direction;
    }
    public String getDirection(){
        return this.direction;
    }
}
