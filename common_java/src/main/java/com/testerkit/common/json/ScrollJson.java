package com.testerkit.common.json;

import com.testerkit.common.enums.ScrollDirection;

/**
 * Created by able on 2018/9/28.
 */

public class ScrollJson {
    private int times;
    /**
     *  毫秒
     */
    private int timeout;
    private boolean toCenter;
    private ScrollDirection direction;

    public void setTimes(int times){
        this.times = times;
    }

    /**
     *
     * @return timeout 毫秒
     */
    public int getTimes(){
        return this.times;
    }

    public int getTimeout() {
        return timeout;
    }

    /**
     * @param timeout 毫秒
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setToCenter(boolean toCenter){
        this.toCenter = toCenter;
    }
    public boolean getToCenter(){
        return this.toCenter;
    }
    public void setDirection(ScrollDirection direction){
        this.direction = direction;
    }
    public ScrollDirection getDirection(){
        return this.direction;
    }
}
