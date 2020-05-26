package com.testerkit.common.pc;

public class PcPoint<T> {
    private String type;
    private T x;
    private T y;
    // 和上一个点的时间间隔，毫秒
    private long duration;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String toDescription() {
        return  String.format("[%s,%s,%s]",x,y,duration);
    }

    @Override
    public String toString() {
        return "PointJson{" +
                "type='" + type + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", duration=" + duration +
                '}';
    }
}
