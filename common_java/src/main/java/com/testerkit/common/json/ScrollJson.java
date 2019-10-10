package com.testerkit.common.json;

import com.testerkit.common.enums.ScrollDirection;

/**
 * Created by able on 2018/9/28.
 */

public class ScrollJson {
    // 滑动的次数
    private int times;
    /**
     *  毫秒
     */
    private int timeout;
    private boolean toCenter;
    private ScrollDirection direction;
    //默认每次滑动间隔1000毫秒
    private long internal = 1*1000;

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

    public boolean isToCenter() {
        return toCenter;
    }

    public long getInternal() {
        return internal;
    }

    public void setInternal(long internal) {
        this.internal = internal;
    }

    public String toDescription() {
        StringBuilder sb = new StringBuilder();
        String strToCenter = this.toCenter ? "元素移动到屏幕中间," : "";
        sb.append("超时:" + timeout + "毫秒,");
        sb.append(strToCenter);
        sb.append("滚动[");
        sb.append(String.format("次数:%s,",times));
        sb.append(String.format("方向:%s,",direction));
        sb.append(String.format("间隔:%s毫秒",internal));
        sb.append("] ");
        return sb.toString();
    }


    @Override
    public String toString() {
        return "ScrollJson{" +
                "times=" + times +
                ", timeout=" + timeout +
                ", toCenter=" + toCenter +
                ", direction=" + direction +
                ", internal=" + internal +
                '}';
    }
}
