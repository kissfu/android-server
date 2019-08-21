/*
 * Copyright (C) 2012 The Android Open SourceClass Project
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
package com.testerkit.uia.utils.dumps;

import android.graphics.Rect;
import android.os.SystemClock;
import android.util.Xml;
import android.view.accessibility.AccessibilityNodeInfo;

import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.model.RectInfo;
import com.testerkit.common.model.UIDumpInfo;
import com.testerkit.common.utils.StopWatch;
import com.testerkit.common.utils.StringUtil;
import com.testerkit.uia.BaseContext;
import com.testerkit.common.exceptions.UIAException;
import com.testerkit.uia.core.DeviceCore;
import com.testerkit.uia.model.ScreenSize;
import com.testerkit.uia.utils.SystemUtil;
import com.testerkit.common.log.Logger;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;

import static com.testerkit.uia.utils.dumps.XMLHierarchy.safeCharSeqToString;


/**
 * The AccessibilityNodeInfoDumper in Android Open SourceClass Project contains a lot of bugs which will
 * stay in old android versions forever. By coping the code of the latest version it is ensured that
 * all patches become available on old android versions. <p/> down ported bugs are e.g. { @link
 * https://code.google.com/p/android/issues/detail?id=62906 } { @link
 * https://code.google.com/p/android/issues/detail?id=58733 }
 */
public class AccessibilityNodeInfoDumper {
    private static String FUNC = "DUMPER";
    private static final String[] NAF_EXCLUDED_CLASSES = new String[]{
            android.widget.GridView.class.getName(),
            android.widget.GridLayout.class.getName(),
            android.widget.ListView.class.getName(),
            android.widget.TableLayout.class.getName()};
    // https://github.com/appium/appium/issues/10204
    private static final int MAX_DEPTH = 70;
    private static int counter_invisible_root = 0;
    private static int counter_invisible_child = 0;

    public static UIDumpInfo uiDumpInfo;

    public static synchronized UIDumpInfo getUIDumpInfo(AccessibilityNodeInfo[] roots) {
        AccessibilityNodeInfoDumper.uiDumpInfo = new UIDumpInfo();
        AccessibilityNodeInfoDumper.uiDumpInfo.setUiXml(getWindowXMLHierarchy(roots));
        return AccessibilityNodeInfoDumper.uiDumpInfo;
    }

    /**
     * Using {@link AccessibilityNodeInfo} this method will walk the layout hierarchy and return
     * String object of xml hierarchy
     *
     * @param roots The root accessibility node.
     */
    public static synchronized String getWindowXMLHierarchy(AccessibilityNodeInfo[] roots) {
        AccessibilityNodeInfoDumper.uiDumpInfo = new UIDumpInfo();
        StopWatch stopWatch = new StopWatch();
        StringWriter xmlDump = new StringWriter();
        try {
            XmlSerializer serializer = Xml.newSerializer();
            serializer.setOutput(xmlDump);
            serializer.startDocument("UTF-8", true);
            serializer.startTag("", "hierarchy");

            if (roots != null && roots.length != 0) {
                DeviceCore device = BaseContext.getInstance().getDevice();
                ScreenSize size = device.getScreenSize();

                final int width = size.getWidth();
                final int height = size.getHeight();

                serializer.attribute("", "rotation", Integer.toString(device.getRotation()));
                for (int i = 0; i < roots.length; i++) {
                    AccessibilityNodeInfo root = roots[i];
                    if (root.isVisibleToUser()) {
                        dumpNodeRec(root, serializer, i, width, height, 0, "");
                        root.recycle();
                    } else {
                        counter_invisible_root++;
//                        Logger.info(String.format("Skipping invisible root: %s", root.toString()));
                    }
                }
            }

            serializer.endTag("", "hierarchy");
            serializer.endDocument();

            /*FileWriter writer = new FileWriter(dumpFile);
            writer.write(stringWriter.toString());
            writer.close();*/
        } catch (IOException e) {
            throw new UIAException("Cannot dump views hierarchy to XML format", e);
        } finally {
            Logger.info(String.format("Skipping invisible root: %s", counter_invisible_root));
            Logger.info(String.format("Skipping invisible child: %s", counter_invisible_child));
            counter_invisible_root = 0;
            counter_invisible_child = 0;
        }
        final long endTime = SystemClock.uptimeMillis();
        Logger.iFunc(FUNC, "Fetch time: ", stopWatch.toElapsedMS());
        AccessibilityNodeInfoDumper.uiDumpInfo.setUiXml(xmlDump.toString());
        return AccessibilityNodeInfoDumper.uiDumpInfo.getUiXml();
    }

    private static void dumpNodeRec(AccessibilityNodeInfo node, XmlSerializer serializer, int index, int width, int height, final int depth, String xpathParent) throws IOException {
        // Some views might have unlimited number of children:
        // https://bugs.chromium.org/p/chromium/issues/detail?id=805014
        if (depth >= MAX_DEPTH) {
            Logger.error(String.format("The xml tree dump has reached its maximum depth of %s at " +
                    "%s. The recursion is stopped to avoid StackOverflowError", MAX_DEPTH, node.toString()));
            return;
        }

        serializer.startTag("", "node");
        NodeInfo myNode = new NodeInfo();
        String indexStr = Integer.toString(index);
        serializer.attribute("", "index", indexStr);
        myNode.setIndex(index);
        String xpath = indexStr;
        if (false == StringUtil.isNullOrEmpty(xpathParent)) {
            xpath = String.format("%s-%s", xpathParent, xpath);
        }
        serializer.attribute("", "xpath", xpath);
        myNode.setXpathSimple(xpath);

        final String text = safeCharSeqToString(node.getText());
        serializer.attribute("", "text", text);
        myNode.setText(text);
        String clazzName = safeCharSeqToString(node.getClassName());
        serializer.attribute("", "class", clazzName);
        myNode.setClazzName(clazzName);
        String packageName = safeCharSeqToString(node.getPackageName());
        serializer.attribute("", "package", packageName);
        myNode.setPackageName(packageName);
        String contentDesc = safeCharSeqToString(node.getContentDescription());
        serializer.attribute("", "content-desc", contentDesc);
        myNode.setContentDesc(contentDesc);
        serializer.attribute("", "checkable", Boolean.toString(node.isCheckable()));
        myNode.setCheckable(node.isCheckable());
        serializer.attribute("", "checked", Boolean.toString(node.isChecked()));
        myNode.setChecked(node.isChecked());
        serializer.attribute("", "clickable", Boolean.toString(node.isClickable()));
        myNode.setClickable(node.isClickable());
        serializer.attribute("", "enabled", Boolean.toString(node.isEnabled()));
        myNode.setEnabled(node.isEnabled());
        serializer.attribute("", "focusable", Boolean.toString(node.isFocusable()));
        myNode.setFocusable(node.isFocusable());
        serializer.attribute("", "focused", Boolean.toString(node.isFocused()));
        myNode.setFocused(node.isFocused());
        serializer.attribute("", "scrollable", Boolean.toString(node.isScrollable()));
        myNode.setScrollable(node.isScrollable());
        serializer.attribute("", "long-clickable", Boolean.toString(node.isLongClickable()));
        myNode.setLongClickable(node.isLongClickable());
        serializer.attribute("", "password", Boolean.toString(node.isPassword()));
        myNode.setPassword(node.isPassword());
        serializer.attribute("", "selected", Boolean.toString(node.isSelected()));
        myNode.setSelected(node.isSelected());
        Rect bounds = AccessibilityNodeInfoHelper.getVisibleBoundsInScreen(node, width, height);
        serializer.attribute("", "bounds", bounds.toShortString());
        myNode.setBounds(bounds.toShortString());
        myNode.setRectVisible(new RectInfo(bounds.left, bounds.top, bounds.right, bounds.bottom));
        String resourceId = "";
        boolean isEditable = false;
        if (SystemUtil.API_LEVEL() >= 18) {
            resourceId = LocationHelpers.getID(safeCharSeqToString(node.getViewIdResourceName()));
            isEditable = node.isEditable();
        }
        serializer.attribute("", "name", resourceId);
        myNode.setName(resourceId);
        if (!isEditable) {
            if (clazzName.equals("android.widget.EditText") || clazzName.toLowerCase().contains("edit")) {
                isEditable = true;
            }
        }
        serializer.attribute("", "editable", Boolean.toString(isEditable));
        myNode.setEnabled(isEditable);
        String nodeType = "uia";
        serializer.attribute("", "node-type", nodeType);
        myNode.setNodeType(nodeType);
        int count = node.getChildCount();
        myNode.setLeaf(count == 0);
        for (int i = 0; i < count; i++) {
            AccessibilityNodeInfo child = node.getChild(i);
            if (child != null) {
                if (child.isVisibleToUser()) {
                    dumpNodeRec(child, serializer, i, width, height, depth + 1, xpath);
                    child.recycle();
                } else {
                    counter_invisible_child++;
//                    Logger.info(String.format("Skipping invisible child: %s", child.toString()));
                }
            } else {
                Logger.info(String.format("Null child %s/%s, parent: %s", i, count, node.toString()));
            }
        }
        serializer.endTag("", "node");
        uiDumpInfo.addNode(myNode);
    }


}
