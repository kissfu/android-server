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
package com.testerkit.uia2.core;


import android.view.InputEvent;
import android.view.MotionEvent;

import com.testerkit.uia.core.InteractionController;
import com.testerkit.uia.core.ReturningRunnable;
import com.testerkit.uia.exceptions.UIAException;

import static com.testerkit.uia.utils.ReflectionUtils.invoke;
import static com.testerkit.uia.utils.ReflectionUtils.method;

public  class InteractionController2 extends InteractionController {

    protected static final String METHOD_PERFORM_MULTI_POINTER_GESTURE = "performMultiPointerGesture";

    public InteractionController2(Object interactionController) {
        super(interactionController);
    }


    //region extends InteractionController

    @Override
    protected String CLASS_INTERACTION_CONTROLLER() {
        return "android.support.test.uiautomator.InteractionController";
    }

    @Override
    public boolean injectEventSync(final InputEvent event) throws UIAException {
        return EventRegister2.runAndRegisterScrollEvents(new ReturningRunnable<Boolean>() {
            @Override
            public void run() {
                Boolean result = (Boolean) invoke(method(CLASS_INTERACTION_CONTROLLER(),
                        METHOD_INJECT_EVENT_SYNC, InputEvent.class), interactionController, event);
                setResult(result);
            }
        });
    }
    @Override
    public boolean touchDown(final int x, final int y) throws UIAException {
        return EventRegister2.runAndRegisterScrollEvents(new ReturningRunnable<Boolean>() {
            @Override
            public void run() {
                Boolean result = (Boolean) invoke(method(CLASS_INTERACTION_CONTROLLER(),
                        METHOD_TOUCH_DOWN, int.class, int.class), interactionController, x, y);
                setResult(result);
            }
        });
    }
    @Override
    public boolean touchUp(final int x, final int y) throws UIAException {
        return EventRegister2.runAndRegisterScrollEvents(new ReturningRunnable<Boolean>() {
            @Override
            public void run() {
                Boolean result = (Boolean) invoke(method(CLASS_INTERACTION_CONTROLLER(), METHOD_TOUCH_UP,
                        int.class, int.class), interactionController, x, y);
                setResult(result);
            }
        });
    }
    @Override
    public boolean touchMove(final int x, final int y) throws UIAException {
        return EventRegister2.runAndRegisterScrollEvents(new ReturningRunnable<Boolean>() {
            @Override
            public void run() {
                Boolean result = (Boolean) invoke(method(CLASS_INTERACTION_CONTROLLER(),
                        METHOD_TOUCH_MOVE, int.class, int.class), interactionController, x, y);
                setResult(result);
            }
        });
    }

    //endregion

    public Boolean performMultiPointerGesture(final MotionEvent.PointerCoords[][] pcs) throws UIAException {
        return EventRegister2.runAndRegisterScrollEvents(new ReturningRunnable<Boolean>() {
            @Override
            public void run() {
                Boolean result = (Boolean) invoke(method(CLASS_INTERACTION_CONTROLLER(),
                        METHOD_PERFORM_MULTI_POINTER_GESTURE, MotionEvent.PointerCoords[][].class),
                        interactionController, (Object) pcs);
                setResult(result);
            }
        });
    }
}
