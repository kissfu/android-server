package com.testerkit.common.pc;

/**
 * @atuthor able
 */
public class PcLayoutParam {
    private int vmax = 5;// ：  0-50(默认值5)，颜色越深值可以设置成越小，纯黑字体可以设置成0
    private int expandWidth = 15;//： 0-30(默认值15)，值越大横向膨胀越宽
    private int expandHeight = 8;//：0-15(默认值8)，值越大纵向膨胀越高
    private int isTable = 0;//：0/1(默认值0)，是否去除表格干扰


    public int getVmax() {
        return vmax;
    }

    public void setVmax(int vmax) {
        this.vmax = vmax;
    }

    public int getExpandWidth() {
        return expandWidth;
    }

    public void setExpandWidth(int expandWidth) {
        this.expandWidth = expandWidth;
    }

    public int getExpandHeight() {
        return expandHeight;
    }

    public void setExpandHeight(int expandHeight) {
        this.expandHeight = expandHeight;
    }

    public int getIsTable() {
        return isTable;
    }

    public void setIsTable(int isTable) {
        this.isTable = isTable;
    }
}
