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

package com.testerkit.uia.utils;

import android.hardware.input.InputManager;
import android.view.InputEvent;

import com.testerkit.common.log.Logger;
import com.testerkit.common.utils.ReflectionUtil;
import com.testerkit.uia.BaseContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class InteractionUtils {
    public static boolean injectEventSync(InputEvent event) {
        return BaseContext.getInstance().getDevice().getUiAutomatorBridge().getInteractionController().injectEventSync(event);
    }


    /**
     * android.os.Build.VERSION.SDK_INT < 18 才起作用
     * @param event
     * @return
     */

    @Deprecated
    public static boolean injectEventSync2(InputEvent event) {
        boolean success = false;
        try {

            //region test1

            InputManager  im = (InputManager) InputManager.class.getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            //MotionEvent.class.getDeclaredMethod("obtain", new Class[0]).setAccessible(true);
            Method injectInputEventMethod = InputManager.class.getMethod("injectInputEvent", new Class[]{InputEvent.class, Integer.TYPE});
            success = (Boolean) injectInputEventMethod.invoke(im, new Object[]{event, Integer.valueOf(2)});

            if(true){
                return  success;
            }

            //endregion

            //region test2

            final Class c = Class.forName("android.hardware.input.InputManager");
            final Method getInstance = ReflectionUtil.method(c, "getInstance");

            final Object instance = getInstance.invoke(null);

//            Class<?> types[] = new Class[2];
//            types[0] = KeyEvent.class;
//            types[1] = int.class;
            //无法获取，不知道为什么
//            final Method inject = ReflectionUtil.method(instance.getClass(), "injectInputEvent",types);
            Method methods[] = InputManager.class.getMethods();
            Method inject = null;
            for (Method m : methods) {
                if (m.getName().equals("injectInputEvent")) {
                    inject = m;
                    break;
                }
            }
            if(inject == null){
                return success;
            }
            inject.setAccessible(true);
            Object params[] = new Object[2];
            params[0] = event;
            params[1] = new Integer(2);
            success = (Boolean) inject.invoke(instance, params);

        } catch (IllegalAccessException e) {
            Logger.error("Failed to injectInputEvent. ", e);
        } catch (InvocationTargetException e) {
            Logger.error("Failed to injectInputEvent. ", e);
        } catch (ClassNotFoundException e) {
            Logger.error("Failed to injectInputEvent. ", e);
        }catch (Exception e) {
            Logger.error("Failed to injectInputEvent. ", e);
        }
        return success;

        //endregion
    }
}
