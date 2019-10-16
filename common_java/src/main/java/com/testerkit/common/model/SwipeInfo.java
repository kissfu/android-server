package com.testerkit.common.model;

/**
 * @atuthor able
 */
public class SwipeInfo {

    private int startX = 0;
    private int startY = 0;
    private int endX = 0;
    private int endY = 0;
    private int steps = 10;

    public SwipeInfo() {
    }

    public SwipeInfo(int startX, int startY, int endX, int endY, int steps) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.steps = steps;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }


    @Override
    public String toString() {
        return "SwipeInfo{" +
                "startX=" + startX +
                ", startY=" + startY +
                ", endX=" + endX +
                ", endY=" + endY +
                ", steps=" + steps +
                '}';
    }
}
