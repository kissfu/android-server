package com.testerkit.common.pc;

/**
 * @atuthor able
 */
public class PcKeyboard {


    private String type; //事件类型  keyup keydown...
    private Integer keyCode; //键盘码
    private String code;//F1 , F2
    private Integer button;  //鼠标左/中/右键 0/1/2
    private boolean altKey;  //alt是否被按下
    private boolean ctrlKey; //ctrl是否被按下
    private boolean shiftKey; //shift是否被按下
    private PcPoint point;      //鼠标位置
    private Integer wheel;    //滚动数值


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(Integer keyCode) {
        this.keyCode = keyCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getButton() {
        return button;
    }

    public void setButton(Integer button) {
        this.button = button;
    }

    public boolean isAltKey() {
        return altKey;
    }

    public void setAltKey(boolean altKey) {
        this.altKey = altKey;
    }

    public boolean isCtrlKey() {
        return ctrlKey;
    }

    public void setCtrlKey(boolean ctrlKey) {
        this.ctrlKey = ctrlKey;
    }

    public boolean isShiftKey() {
        return shiftKey;
    }

    public void setShiftKey(boolean shiftKey) {
        this.shiftKey = shiftKey;
    }

    public PcPoint getPoint() {
        return point;
    }

    public void setPoint(PcPoint point) {
        this.point = point;
    }

    public Integer getWheel() {
        return wheel;
    }

    public void setWheel(Integer wheel) {
        this.wheel = wheel;
    }
}
