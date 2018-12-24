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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import javax.xml.soap.Node;


/**
 * UiElementNode that implements the common operations.
 */
public class UiElementNode extends UiElement<NodeInfo, UiElementNode>  {

    private final static Map<NodeInfo, UiElementNode> cache = new WeakHashMap<NodeInfo, UiElementNode>();
    private  Map<Attribute, Object> attributes ;
    private final List<UiElementNode> children = new ArrayList<UiElementNode>();
    private  UiElementNode parent;
    private int rotation;

    public UiElementNode() {
    }

    public void put(String key, Object value) {
        if (value != null) {
            attributes.put(Attribute.valueOf(key), value);
        }
    }
    public void put(Map<Attribute, Object> attribs, Attribute key, Object value) {
        if (value != null) {
            attribs.put(key, value);
        }
    }

    public void setParent(UiElementNode parent) {
        this.parent = parent;
    }

    public UiElementNode getParent() {
        return parent;
    }

    public void addChild(UiElementNode child){
        child.parent = this;
        children.add(child);
    }


    @Override
    protected List<UiElementNode> getChildren() {
        return children;
    }

    @Override
    protected Map<Attribute, Object> getAttributes() {
        return attributes;
    }
}
