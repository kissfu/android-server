package com.testerkit.common.json;

/**
 * Created by able on 2018/9/28.
 */

public class ScrollJson {
    private int times;
    /**
     *  毫秒
     */
    private int timeOut ;
    private boolean toCenter;
    private String direction;

    public void setTimes(int times){
        this.times = times;
    }

    /**
     *
     * @return timeOut 毫秒
     */
    public int getTimes(){
        return this.times;
    }

    public int getTimeOut() {
        return timeOut;
    }

    /**
     * @param timeOut 毫秒
     */
    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public void setToCenter(boolean toCenter){
        this.toCenter = toCenter;
    }
    public boolean getToCenter(){
        return this.toCenter;
    }
    public void setDirection(String direction){
        this.direction = direction;
    }
    public String getDirection(){
        return this.direction;
    }
}
