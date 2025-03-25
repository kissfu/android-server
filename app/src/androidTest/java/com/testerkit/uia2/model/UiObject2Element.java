/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.testerkit.uia2.model;

import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import com.testerkit.common.log.Logger;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.exceptions.InvalidCoordinatesException;
import com.testerkit.uia.model.AndroidElement;
import com.testerkit.uia.utils.elements.Point;
import com.testerkit.uia.utils.elements.PositionHelper;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;

import static com.testerkit.common.utils.ReflectionUtil.getField;


public class UiObject2Element implements AndroidElement {

    private final UiObject2 element;
    private final String id;


    public UiObject2Element(String id, UiObject2 element) {
        this.id = id;
        this.element = element;
    }

    private static boolean isToastElement(AccessibilityNodeInfo nodeInfo) {
        return nodeInfo.getClassName().toString().equals(Toast.class.getName());
    }

    //region

    @Override
    public void click() {
        element.click();
    }
    @Override
    public boolean longClick() {
        element.longClick();
        return true;
    }
    @Override
    public String getText() {
        AccessibilityNodeInfo nodeInfo = (AccessibilityNodeInfo) getField(UiObject2.class,
                "mCachedNode", element);
        /*
         * If the given element is TOAST element, we can't perform any operation on {@link UiObject2} as it
         * not formed with valid AccessibilityNodeInfo, Instead we are using custom created AccessibilityNodeInfo of
         * TOAST Element to retrieve the Text.
         */
        if (isToastElement(nodeInfo)) {
            return nodeInfo.getText().toString();
        }

//        if (nodeInfo.getRangeInfo() != null) {
//            /* Refresh accessibility node info to get actual state of element */
//            nodeInfo = AccessibilityNodeInfoGetter.fromUiObject(element);
//            return Float.toString(nodeInfo.getRangeInfo().getCurrent());
//        }
        // on null returning empty string
        return element.getText() != null ? element.getText() : "";
    }
    @Override
    public String getName() {
        return element.getContentDescription();
    }

    @Override
    public String getClassName() throws Exception {
        return element.getClassName();
    }


    @Override
    public boolean setText(final String text, boolean unicodeKeyboard){
        element.setText(text);
        return true;
    }

    @Override
    public void clear() {
        element.clear();
    }
    @Override
    public String getId() {
        return this.id;
    }
    @Override
    public Rect getBounds() {
        return element.getVisibleBounds();
    }
    @Override
    public String getContentDesc() {
        return element.getContentDescription();
    }
    @Override
    public UiObject2 getUiObject() {
        return element;
    }
    @Override
    public Point getAbsolutePosition(final Point point)
            throws InvalidCoordinatesException {
        final Rect rect = this.getBounds();

        Logger.debug("Element bounds: " + rect.toShortString());

        return PositionHelper.getAbsolutePosition(point, rect, new Point(rect.left, rect.top), false);
    }

    @Override
    public String getResourceId() {
        return element.getResourceName();
    }

    @Override
    public boolean dragTo(Object destObj, int steps) throws UiObjectNotFoundException {
        if (destObj instanceof UiObject) {
            int destX = ((UiObject) destObj).getBounds().centerX();
            int destY = ((UiObject) destObj).getBounds().centerY();
            element.drag(new android.graphics.Point(destX, destY), steps);
            return true;
        } else if (destObj instanceof UiObject2) {
            android.graphics.Point coord = ((UiObject2) destObj).getVisibleCenter();
            element.drag(coord, steps);
            return true;
        } else {
            Logger.error("Destination should be either UiObject or UiObject2");
            return false;
        }
    }

    @Override
    public boolean dragTo(int destX, int destY, int steps) throws InvalidCoordinatesException {
        Point coords = new Point(destX, destY);
        coords = PositionHelper.getDeviceAbsPos(coords);
        element.drag(new android.graphics.Point(coords.x.intValue(), coords.y.intValue()), steps);
        return true;
    }

    //endregion


    //region type text

    @Override
    public boolean typeDefault(String text){
        boolean success = false;

        try {
            UiObject2 uiObject = getFocusedEditObject();
            if(uiObject != null){
                uiObject.setText(text);
                return true;
            }
            uiObject = getEditObject();

            if(uiObject != null){
                uiObject.setText(text);
                return true;
            }
        }catch (Exception e){
            Logger.error(e);
        }

        return success;
    }

    private UiObject2 getFocusedObject() throws Exception{
        BySelector bySelector = By.focused(true);
        UiDevice uiDevice =  (UiDevice) BaseContext.getInstance().getDevice().getUiDevice();
        return  uiDevice.findObject(bySelector);
    }

    private UiObject2 getFocusedEditObject() throws Exception{
        BySelector bySelector = By.focused(true);
        bySelector.clazz(android.widget.EditText.class);
        UiDevice uiDevice =  (UiDevice) BaseContext.getInstance().getDevice().getUiDevice();
        return  uiDevice.findObject(bySelector);
    }

    private UiObject2 getEditObject()  throws Exception{
        BySelector bySelector = By.clazz(android.widget.EditText.class);
        UiDevice uiDevice =  (UiDevice) BaseContext.getInstance().getDevice().getUiDevice();
        return  uiDevice.findObject(bySelector);

    }



    //endregion
}
