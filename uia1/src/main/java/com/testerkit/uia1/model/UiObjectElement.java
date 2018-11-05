package com.testerkit.uia1.model;

import android.graphics.Rect;
//import android.support.annotation.Nullable;
//import android.support.test.uiautomator.BySelector;
//import android.support.test.uiautomator.Configurator;
//import android.support.test.uiautomator.UiObject;
//import android.support.test.uiautomator.UiObject2;
//import android.support.test.uiautomator.Exception;
//import android.support.test.uiautomator.UiSelector;
import android.view.accessibility.AccessibilityNodeInfo;

import com.android.uiautomator.core.Configurator;
import com.android.uiautomator.core.UiObject;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.testerkit.uia.core.DeviceCore;
import com.testerkit.uia.exceptions.InvalidCoordinatesException;
import com.testerkit.uia.exceptions.InvalidSelectorException;
import com.testerkit.uia.exceptions.NoAttributeFoundException;


import com.testerkit.uia.model.AndroidElement;
import com.testerkit.uia.model.serach.By;
import com.testerkit.uia.utils.Logger;
import com.testerkit.uia.utils.ReflectionUtils;
import com.testerkit.uia.utils.elements.Point;
import com.testerkit.uia.utils.elements.PositionHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.testerkit.uia.utils.ReflectionUtils.invoke;
import static com.testerkit.uia.utils.ReflectionUtils.method;


public class UiObjectElement implements AndroidElement {

    private static final Pattern endsWithInstancePattern = Pattern.compile(".*INSTANCE=\\d+]$");
    private final UiObject element;
    private final String id;


    public UiObjectElement(String id, UiObject element) {
        this.id = id;
        this.element = element;

    }


    //region implements AndroidElement

    @Override
    public void click() throws UiObjectNotFoundException {
        element.click();
    }
    @Override
    public boolean longClick() throws UiObjectNotFoundException {
        return element.longClick();
    }
    @Override
    public String getText() throws UiObjectNotFoundException {
        // on null returning empty string
        return element.getText() != null ? element.getText() : "";
    }
    @Override
    public String getName() throws UiObjectNotFoundException {
        return element.getContentDescription();
    }

    @Override
    public String getClassName() throws UiObjectNotFoundException {
        return element.getClassName();
    }

    @Override
    public boolean setText(final String text, boolean unicodeKeyboard) throws UiObjectNotFoundException {
        return element.setText(text);
    }
    @Override
    public void clear() throws UiObjectNotFoundException {
        element.setText("");
    }
    @Override
    public String getId() {
        return this.id;
    }
    @Override
    public Rect getBounds() throws UiObjectNotFoundException {
        Rect rectangle = element.getVisibleBounds();
        return rectangle;
    }


    @Override
    public String getContentDesc() throws UiObjectNotFoundException {
        return element.getContentDescription();
    }

    @Override
    public UiObject getUiObject() {
        return element;
    }

    @Override
    public Point getAbsolutePosition(final Point point)
            throws UiObjectNotFoundException, InvalidCoordinatesException {
        final Rect rect = this.getBounds();

        Logger.debug("Element bounds: " + rect.toShortString());

        return PositionHelper.getAbsolutePosition(point, rect, new Point(rect.left, rect.top), false);
    }

    @Override
    public String getResourceId() {
        String resourceId = "";

        try {
            /*
             * Unfortunately UiObject does not implement a getResourceId method.
             * There is currently no way to determine the resource-id of a given
             * element represented by UiObject. Until this support is added to
             * UiAutomater, we try to match the implementation pattern that is
             * already used by UiObject for getting attributes using reflection.
             * The returned string matches exactly what is displayed in the
             * UiAutomater inspector.
             */
            AccessibilityNodeInfo node = (AccessibilityNodeInfo) invoke(method(element.getClass(), "findAccessibilityNodeInfo", long.class),
                    element, Configurator.getInstance().getWaitForSelectorTimeout());

            if (node == null) {
                throw new UiObjectNotFoundException(element.getSelector().toString());
            }

            resourceId = node.getViewIdResourceName();
        } catch (final Exception e) {
            Logger.error("Exception: " + e + " (" + e.getMessage() + ")");
        }

        return resourceId;
    }

    @Override
    public boolean dragTo(final int destX, final int destY, final int steps)
            throws UiObjectNotFoundException, InvalidCoordinatesException {
        Point coords = new Point(destX, destY);
        coords = PositionHelper.getDeviceAbsPos(coords);
        return element.dragTo(coords.x.intValue(), coords.y.intValue(), steps);
    }

    @Override
    public boolean dragTo(final Object destObj, final int steps)
            throws UiObjectNotFoundException, InvalidCoordinatesException {
        if (destObj instanceof UiObject) {
            return element.dragTo((UiObject) destObj, steps);
        }

        Logger.error("Destination should be either UiObject or UiObject2");
        return false;
    }


    //endregion

    //region type text

    @Override
    public boolean typeDefault(String text){
        boolean success = false;

        try {
            UiObject uiObject = getFocusedObject();
            if(uiObject != null && uiObject.exists() && uiObject.getClassName().equals("android.widget.EditText")){
                return  uiObject.setText(text);
            }
            uiObject = getEditObject();

            if(uiObject != null && uiObject.exists()){
                return  uiObject.setText(text);
            }
        }catch (Exception e){
            Logger.error(e);
        }

        return success;
    }

    private UiObject getFocusedObject() throws Exception{
        return new UiObject(new UiSelector().focusable(true));
    }

    private UiObject getEditObject()  throws Exception{
        return new UiObject(new UiSelector().className(android.widget.EditText.class).instance(0));
    }


    //endregion
}
