/*
 * Copyright (C) 2012 The Android Open Source Project
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
package com.testerkit.uia.core;

import android.view.InputEvent;
import android.view.MotionEvent.PointerCoords;

import com.testerkit.uia.exceptions.UIAException;


import static com.testerkit.uia.utils.ReflectionUtils.invoke;
import static com.testerkit.uia.utils.ReflectionUtils.method;

public  abstract class InteractionController {


    protected abstract String CLASS_INTERACTION_CONTROLLER();

    protected static final String METHOD_SEND_KEY = "sendKey";
    protected static final String METHOD_INJECT_EVENT_SYNC = "injectEventSync";
    protected static final String METHOD_TOUCH_DOWN = "touchDown";
    protected static final String METHOD_TOUCH_UP = "touchUp";
    protected static final String METHOD_TOUCH_MOVE = "touchMove";
    protected final Object interactionController;

    public InteractionController(Object interactionController) {
        this.interactionController = interactionController;
    }


    public boolean sendKey(int keyCode, int metaState) throws UIAException {
        return (Boolean) invoke(method(CLASS_INTERACTION_CONTROLLER(), METHOD_SEND_KEY, int.class, int.class), interactionController, keyCode, metaState);
    }

    public abstract boolean injectEventSync(final InputEvent event);

    public abstract boolean touchDown(final int x, final int y) ;

    public abstract boolean touchUp(final int x, final int y);

    public abstract boolean touchMove(final int x, final int y) ;

}
