package com.testerkit.uia.model;

/**
 * Created by able on 2018/9/10.
 */

public class ScreenSize {
    private int width;
    private int height;

    public ScreenSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

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

    @Override
    public String toString() {
        return "ScreenSize{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
