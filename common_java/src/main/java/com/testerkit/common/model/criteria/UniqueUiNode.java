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

package com.testerkit.common.model.criteria;


import com.testerkit.common.enums.Attribute;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.utils.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class UniqueUiNode {

    private List<CriteriaBase> criterias = new ArrayList<CriteriaBase>();
    private List<UniqueGroup> groups = new ArrayList<UniqueGroup>();
    private NodeInfo nodeInfo;

    public UniqueUiNode(NodeInfo node) {
        this.nodeInfo = node;
        init(node);
    }

    /**
     * 1、如果某个字段为空不进行全组合
     * 2、注意criterias的添加顺序，NAME->CLASS->TEXT->CONTENT_DESC.在组合元素个数为1的情况下的时候按顺序优先选择
     */
    public void init(NodeInfo node) {
        criterias.clear();
        groups.clear();
        if (StringUtil.isNotNullOrEmpty(node.getName())) {
            criterias.add(new CriteriaBase(Attribute.NAME, node.getName()));
        }
        if (StringUtil.isNotNullOrEmpty(node.getClazzName())) {
            criterias.add(new CriteriaBase(Attribute.CLASS, node.getClazzName()));
        }
        if (StringUtil.isNotNullOrEmpty(node.getText())) {
            criterias.add(new CriteriaBase(Attribute.TEXT, node.getText()));
        }
        if (StringUtil.isNotNullOrEmpty(node.getContentDesc())) {
            criterias.add(new CriteriaBase(Attribute.CONTENT_DESC, node.getContentDesc()));
        }
        initGroups(criterias);
        if (criterias.size() == 0) {
            System.out.println("--->no unique:" + criterias.size() + "," + groups.size());
        }
    }

    /**
     * 求所有组合也就是abc各个位是否选取的问题，第一位2中可能，第二位2种。。。所以一共有2^n种。用0表示不取，1表示选取，这样可以用110这样的形式表示ab。abc一共的表示形式从0到2^3-1。然后按位与运算，如果结果为1就输出当前位，结果0不输出。
     *
     * @param chs
     */
    private void initGroups(List<CriteriaBase> list) {
        int len = list.size();
        int nbits = 1 << len;
        for (int i = 0; i < nbits; ++i) {
            int t;
            List<CriteriaBase> groupOne = new ArrayList<CriteriaBase>();
            for (int j = 0; j < len; j++) {
                t = 1 << j;
                if ((t & i) != 0) { // 与运算，同为1时才会是1
                    //System.out.print(chs[j]);
                    groupOne.add(list.get(j));
                }
            }
            if (groupOne.size() > 0) {
                groups.add(new UniqueGroup(groupOne));
            }
            //System.out.println(i + "," + StringUtil.join(groupOne.toArray(), "->"));
        }
    }

    public void groupsUnique(NodeInfo nodeOther) {
        if (criterias.size() == 0) {
            return;
        }
        for (UniqueGroup g : groups) {
            boolean isOk = g.isSame(nodeOther);
            if (isOk) {
                g.increase();
            }
        }
    }

    /**
     * 返回最小唯一性组合（尽量少的条件）
     *
     * @return [@class='' and text='']
     */
    public String getGroupUniMini() {
        String result = "";
        //1、需要对groups进行排序，是单组最小的list.size()排在最前面
        //2、升序排列
        Collections.sort(groups);

        for (UniqueGroup g : groups) {
            if (g.isUnique()) {
                return g.getUniMini();
            }
        }
        return result;
    }

    /**
     * @return [@class='' and text='']
     */
    public String getAttributeSimpleCombo() {
        StringBuilder sb = new StringBuilder();
        if (nodeInfo == null) {
            return sb.toString();
        }
        sb.append(getAttributeSimple(sb.toString(), Attribute.NAME, nodeInfo.getName()));
        sb.append(getAttributeSimple(sb.toString(), Attribute.CLASS, nodeInfo.getClazzName()));
        int index = nodeInfo.getIndex();
        sb.append(getAttributeSimple(sb.toString(), Attribute.INDEX, index == -1 ? "" : index + ""));

        if (sb.length() > 0) {
            return String.format("[%s]", sb);
        }
        return sb.toString();
    }

    private String getAttributeSimple(String xpath, Attribute key, String value) {
        String result = "";
        if (StringUtil.isNullOrEmpty(value)) {
            return result;
        }
        if (StringUtil.isNullOrEmpty(xpath)) {
            result = String.format("@%s='%s'", key, value);
        } else {
            result = String.format(" and @%s='%s'", key, value);
        }

        return result;
    }

    public boolean hasGroupUni() {
        for (UniqueGroup g : groups) {
            if (g.isUnique()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasGroups() {
        return groups.size() > 0;
    }

    public static void main(String[] args) {
        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo.setText("登陆");
        nodeInfo.setName("login");
        nodeInfo.setClazzName("com.android.Button");
        nodeInfo.setContentDesc("this is login button");
        UniqueUiNode unique = new UniqueUiNode(nodeInfo);
        //unique.init(nodeInfo);

        //1
        NodeInfo nodeOther = new NodeInfo();
        nodeOther.setText("登陆");
        nodeOther.setName("login");
        nodeOther.setClazzName("com.android.Button");
        nodeOther.setContentDesc("this is login button");
        unique.groupsUnique(nodeOther);

        //2
//        nodeOther = new NodeInfo();
//        nodeOther.setText("登陆");
//        nodeOther.setName("login2");
//        nodeOther.setClazzName("com.android.Button2");
//        nodeOther.setContentDesc("this is login button2");
//        unique.groupsUnique(nodeOther);

        String str = unique.getGroupUniMini();
        System.out.printf(str);
    }

}
