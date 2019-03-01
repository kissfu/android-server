/*
 * Copyright (C) 2013 DroidDriver committers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.testerkit.common.model;


import com.testerkit.common.enums.Attribute;
import com.testerkit.common.enums.XPathOption;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.criteria.UniqueUiNode;
import com.testerkit.common.utils.RegExUtil;
import com.testerkit.common.utils.StopWatch;
import com.testerkit.common.utils.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;


/**
 * UiElementNode that implements the common operations.
 */
public class UiElementNode extends UiElement<NodeInfo, UiElementNode> {

    //不包含跟元素
    private final static Map<NodeInfo, UiElementNode> cache = new WeakHashMap<NodeInfo, UiElementNode>();
    private Map<Attribute, Object> attributes;
    private final List<UiElementNode> children = new ArrayList<UiElementNode>();
    private UiElementNode parent;
    private UniqueUiNode uniqueUiNode;
    private final static String tagNode = "node";
    private final static String tagRoot = "hierarchy";
    private int rotation;
    private final List<XPathInfo> xpathes = new ArrayList<XPathInfo>();

    public List<XPathInfo> getXpathes() {
        return xpathes;
    }

    public UiElementNode() {
        node = new NodeInfo();
        attributes = new EnumMap<>(Attribute.class);
    }


    public void setParent(UiElementNode parent) {
        this.parent = parent;
    }

    public UiElementNode getParent() {
        return parent;
    }

    public void addChild(UiElementNode child) {
        cache.put(child.node, child);
        removeSameuiNode(child);
        child.parent = this;
        children.add(child);
    }

    public void clearAllChildren() {
        for (UiElementNode child : children) {
            child.clearAllChildren();
        }
        children.clear();
    }

    public void clearAll() {
        cache.clear();
        allNodeInfo.clear();
        allUiNode.clear();
        this.clearAllChildren();
    }

    public String getTagName() {
        if (this.parent != null) {
            return tagNode;
        }
        return tagRoot;
    }

    @Override
    protected List<UiElementNode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    protected Map<Attribute, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return String.format("tag: %s, xp: %s", getTagName(), get(Attribute.XPATH));
    }


    //region init attributes and NodeInfo firstly
    //TODO 初始化NodeInfo 和 属性对应关系
    public void put(Attribute attribute, Object value) {
        String val = value == null ? "" : value.toString();
        switch (attribute) {
            case ROTATION:
                rotation = Integer.valueOf(val);
                break;
            case TEXT:
                node.setText(val);
                break;
            case CONTENT_DESC:
                node.setContentDesc(val);
                break;
            case NAME:
                node.setName(val);
                break;
            case CLASS:
                node.setClazzName(val);
                break;
            case PACKAGE:
                node.setPackageName(val);
                break;
            case INDEX:
                node.setIndex(Integer.valueOf(val));
                break;
            case XPATH:
                node.setXpathSimple(val);
                break;
            case BOUNDS:
                node.setBounds(val);
                node.setRectReal(RegExUtil.getRect(val));
                break;
            case EDITABLE:
                node.setEditable(Boolean.valueOf(val));
                break;
            case FOCUSABLE:
                node.setFocusable(Boolean.valueOf(val));
                break;
            case PASSWORD:
                node.setPassword(Boolean.valueOf(val));
                break;
            case CLICKABLE:
                node.setClickable(Boolean.valueOf(val));
                break;
        }
    }

    public void put(String key, Object value) {
        if (value != null) {
            try {
                Attribute attribute = Attribute.fromString(key);
                attributes.put(attribute, value);
                put(attribute, value);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void put(Map<Attribute, Object> attribs, Attribute key, Object value) {
        if (value != null) {
            attribs.put(key, value);
        }
    }

    //endregion

    //region  isSameBounds handler

    private void removeSameuiNode(UiElementNode uiNode) {
        if (uiNode == null) {
            return;
        }
        synchronized (children) {
            ArrayList<UiElementNode> removeList = new ArrayList<UiElementNode>();
            Iterator<UiElementNode> iteratorList = children.iterator();
            while (iteratorList.hasNext()) {
                UiElementNode node = iteratorList.next();
                if (uiNode.isSameBounds(node) && uiNode.isSameBetterThan(node)) {
                    removeList.add(node);
                }
            }
            for (UiElementNode item : removeList) {
                children.remove(item);
                Logger.info("--->remove same bounds:" + item);
            }
        }
    }

    /**
     * 对比两个位置完全相同的控件，如果有name（resourceId）, text, children,
     * 或者clickable都认为是好的控件，需要保留
     *
     * @return true表示新控件可以完全替代旧控件，false表示两个控件都需要保留
     */
    private boolean isSameBetterThan(UiElementNode uiNode) {

        if (this.node.isEditable() && uiNode.node.isEditable() == false) {
            return true;
        }
        if (this.isSameBetter() && uiNode.isSameBetter() == false) {
            return true;
        }
        return false;
    }

    private boolean isSameBetter() {
        boolean hasName = StringUtil.isNotNullOrEmpty(this.node.getName());
        boolean hasText = StringUtil.isNotNullOrEmpty(this.node.getText()) || StringUtil.isNotNullOrEmpty(this.node.getContentDesc());
        boolean hasChild = this.getChildren().isEmpty() == false;
        boolean isClickable = this.node.isClickable();

        return hasName || hasText || hasChild || isClickable;
    }

    private boolean isSameBounds(UiElementNode nodeOther) {
        if (nodeOther == null) {
            return false;
        }
        try {
            return this.node.getBounds().equals(nodeOther.node.getBounds());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //endregion

    //region unique node and xpath

    private final static List<NodeInfo> allNodeInfo = new ArrayList<NodeInfo>();
    private final static List<UiElementNode> allUiNode = new ArrayList<UiElementNode>();

    public List<NodeInfo> getAllNodeInfo() {
        if (allNodeInfo.size() > 0) {
            return allNodeInfo;
        }
        for (Map.Entry<NodeInfo, UiElementNode> entry : cache.entrySet()) {
            allNodeInfo.add(entry.getKey());
            //list.add(entry.getValue());
        }
        return allNodeInfo;
    }

    public List<UiElementNode> getAllUiNode() {
        if (allUiNode.size() > 0) {
            return allUiNode;
        }
        for (Map.Entry<NodeInfo, UiElementNode> entry : cache.entrySet()) {
            allUiNode.add(entry.getValue());
        }
        return allUiNode;
    }

    /**
     * root 节点不需要生成xpath
     */
    public void generateXPathRecursion() {
        //this.generateXPath(); 获取root跟节点的xpath
        for (UiElementNode uiNode : children) {
            uiNode.generateXPath();
            uiNode.generateXPathRecursion();
        }
    }

    /**
     * root 节点不需要生成xpath
     * 跟节点的时候 xpath为空，所以排除了跟节点生成XPATH
     */
    //TODO 1、多个xpath生成。2、xpath生成方式，通过兄弟定位
    public void generateXPath() {
        this.getUnique();
        String xpath = "";
        StopWatch stopWatch = new StopWatch();
        UiElementNode parent = this;
        String plus = "";
        while (parent != null && parent.getUnique().hasGroupUni() == false) {
            plus = StringUtil.isNullOrEmpty(xpath) ? "" : "/" + xpath;
            xpath = parent.getTagName() + parent.uniqueUiNode.getAttributeSimpleCombo() + plus;
            parent = parent.getParent();
        }
        plus = StringUtil.isNullOrEmpty(xpath) ? "" : "/" + xpath;
        if (parent == null) {
            xpath = plus;
        } else {
            xpath = "//" + getTagName() + parent.uniqueUiNode.getGroupUniMini() + plus;
        }
        XPathInfo xp = new XPathInfo();
        xp.setXpath(xpath);
        xp.setOption(XPathOption.ALL);
        this.xpathes.add(xp);
        Logger.info(stopWatch.toElapsedMS()+"===>" + xpath + ",xp:" + this.get(Attribute.XPATH));
    }

    public UniqueUiNode getUnique() {
        if (this.uniqueUiNode != null) {
            return this.uniqueUiNode;
        }
        this.uniqueUiNode = new UniqueUiNode(this.node);
        //如果groups为空，则不需要继续往下执行
        if (this.uniqueUiNode.hasGroups() == false) {
            return this.uniqueUiNode;
        }
        getAllNodeInfo();
        for (NodeInfo node : allNodeInfo) {
            this.uniqueUiNode.groupsUnique(node);
        }
        return this.uniqueUiNode;
    }

    //endregion

}
