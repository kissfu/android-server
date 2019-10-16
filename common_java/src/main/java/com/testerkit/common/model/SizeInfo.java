package com.testerkit.common.model;

public class SizeInfo {
    public int width;
    public int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public SizeInfo(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public SizeInfo() {
        this(0, 0);
    }

    @Override public String toString() {
        return "Size(" + width + ", " + height + ")";
    }
}
