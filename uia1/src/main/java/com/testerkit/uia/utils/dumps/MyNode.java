package com.testerkit.uia.utils.dumps;

import android.graphics.Rect;

import java.util.UUID;

public class MyNode {


    /**
     * uuid
     */
    private String id = UUID.randomUUID().toString();

    /**
     * 唯一名称
     */
    private String name="";

    /**
     * 表示该元素是什么类型的uia，h5，x5
     */
    private String nodeType = "";

    /**
     * 控件可见区域（只会控件在界面可见部分的区域，不可见的会被忽略）
     */
    private Rect rectVisible;
    /**
     * 控件的真实区域
     */
    private Rect rectReal;

    /**
     * 控件node文本内容
     */
    private String text = "";

    /**
     * 盲人辅助朗读文本
     */
    private String  contentDesc = "";

    /**
     * 是否是叶子节点
     */
    private boolean isLeaf;

    private int index;
    private boolean isPassword;
    private boolean isEditable;
    /**
     * 是否可以被checkable的控件
     */
    private boolean isCheckable;

    /**
     * 如果是checkbox, 是否是勾选状态
     */
    private boolean isChecked ;
    private boolean isClickable;
    private boolean isEnabled;
    private boolean isFocusable;
    private boolean isFocused;
    private boolean isLongClickable;
    private boolean isSelected;
    private boolean isScrollable;

    private String bounds;
    private String clazzName = "";
    private String packageName = "";
    private String xpathSimple = "";


    //region getter setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public Rect getRectVisible() {
        return rectVisible;
    }

    public void setRectVisible(Rect rectVisible) {
        this.rectVisible = rectVisible;
    }

    public Rect getRectReal() {
        return rectReal;
    }

    public void setRectReal(Rect rectReal) {
        this.rectReal = rectReal;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isPassword() {
        return isPassword;
    }

    public void setPassword(boolean password) {
        isPassword = password;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    public boolean isCheckable() {
        return isCheckable;
    }

    public void setCheckable(boolean checkable) {
        isCheckable = checkable;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isClickable() {
        return isClickable;
    }

    public void setClickable(boolean clickable) {
        isClickable = clickable;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isFocusable() {
        return isFocusable;
    }

    public void setFocusable(boolean focusable) {
        isFocusable = focusable;
    }

    public boolean isFocused() {
        return isFocused;
    }

    public void setFocused(boolean focused) {
        isFocused = focused;
    }

    public boolean isLongClickable() {
        return isLongClickable;
    }

    public void setLongClickable(boolean longClickable) {
        isLongClickable = longClickable;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isScrollable() {
        return isScrollable;
    }

    public void setScrollable(boolean scrollable) {
        isScrollable = scrollable;
    }

    public String getBounds() {
        return bounds;
    }

    public void setBounds(String bounds) {
        this.bounds = bounds;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getXpathSimple() {
        return xpathSimple;
    }

    public void setXpathSimple(String xpathSimple) {
        this.xpathSimple = xpathSimple;
    }
//endregion


    @Override
    public String toString() {
        return "MyNode{" +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", contentDesc='" + contentDesc + '\'' +
                ", clazzName='" + clazzName + '\'' +
                ", xpathSimple='" + xpathSimple + '\'' +
                ", bounds='" + bounds + '\'' +
                ", packageName='" + packageName + '\'' +
                ", rectVisible=" + rectVisible +
                ", rectReal=" + rectReal +
                ", nodeType='" + nodeType + '\'' +
                '}';
    }
}
