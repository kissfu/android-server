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

package com.testerkit.uia.model;

import android.graphics.Rect;

import com.testerkit.uia.exceptions.InvalidCoordinatesException;
import com.testerkit.uia.utils.elements.Point;


public interface AndroidElement {


    void clear() throws Exception;

    void click() throws Exception;

    boolean longClick() throws Exception;

    String getText() throws Exception;

    String getName() throws Exception;

    String getClassName() throws Exception;

    boolean setText(final String text, boolean unicodeKeyboard) throws Exception;

    String getId();

    Rect getBounds() throws Exception;

    String getContentDesc() throws Exception;

    Object getUiObject();

    Point getAbsolutePosition(final Point point)
            throws Exception, InvalidCoordinatesException;

    String getResourceId();

    boolean dragTo(final int destX, final int destY, final int steps)
            throws Exception, InvalidCoordinatesException;

    boolean dragTo(final Object destObj, final int steps)
            throws Exception, InvalidCoordinatesException;

    boolean typeDefault(String text);
}
