package com.testerkit.common.json;

public class PointJson {
    private String type;
    private double x;
    private double y;
    // 和上一个点的时间间隔，毫秒
    private long duration;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
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
