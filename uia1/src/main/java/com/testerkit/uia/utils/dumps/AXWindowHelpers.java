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

package com.testerkit.uia.utils.dumps;

import android.os.Build;
import android.os.SystemClock;
import android.view.accessibility.AccessibilityNodeInfo;
//import android.view.accessibility.AccessibilityWindowInfo;

import com.testerkit.uia.BaseContext;
import com.testerkit.uia.core.UiAutomatorBridge;
import com.testerkit.common.exceptions.UIAException;
import com.testerkit.uia.model.UiAutomationElement;
import com.testerkit.common.log.Logger;

import java.util.ArrayList;
import java.util.List;

//import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
//import io.appium.uiautomator2.core.UiAutomatorBridge;
//import io.appium.uiautomator2.model.NotificationListener;
//import io.appium.uiautomator2.model.UiAutomationElement;
//import io.appium.uiautomator2.model.internal.CustomUiDevice;

public class AXWindowHelpers {
    public static final long AX_ROOT_RETRIEVAL_TIMEOUT = 10000;
    private static final boolean MULTI_WINDOW = false;
//    private static AccessibilityNodeInfo currentActiveWindowRoot = null;

//    public static void refreshRootAXNode() throws UIAException {
//        BaseContext.getInstance().getDevice().waitForIdle();
//        // This call invokes `AccessibilityInteractionClient.getInstance().clearCache();` method
//        // which resets the internal accessibility cache
//        //noinspection EmptyCatchBlock
//        try {
//            //UIA1会报错, 暂时还没有UiAutomation
//            UiAutomatorBridge.getInstance().getUiAutomation().setServiceInfo(null);
//        } catch (Exception ign) {}
//
//        long end = SystemClock.uptimeMillis() + AX_ROOT_RETRIEVAL_TIMEOUT;
//        while (end > SystemClock.uptimeMillis()) {
//            AccessibilityNodeInfo root = null;
//            try {
//                root = UiAutomatorBridge.getInstance().getQueryController().getAccessibilityRootNode();
//            } catch (Exception e) {
//                /*
//                 * Sometimes getAccessibilityRootNode() throws
//                 * "java.lang.IllegalStateException: Cannot perform this action on a sealed instance."
//                 * Ignore it and try to re-get root node.
//                 */
//                Logger.debug(String.format("'%s' exception was caught while invoking " +
//                        "getRootAccessibilityNodeInActiveWindow() - ignoring it", e.getMessage()));
//            }
//            if (root != null) {
//                List<CharSequence> toastMSGs = null;
//                try {
//                    toastMSGs = UiAutomatorBridge.getInstance().getUiAutomation().getAccessibilityEventListener().getToastMessage();
//                }catch (Exception ign) {}
//                UiAutomationElement.rebuildForNewRoot(root, toastMSGs);
//                currentActiveWindowRoot = root;
//                return;
//            }
//        }
//        throw new UIAException(String.format(
//                "Timed out after %d milliseconds waiting for root AccessibilityNodeInfo",
//                AX_ROOT_RETRIEVAL_TIMEOUT));
//    }

    /**
     * Returns a list containing the root {@link AccessibilityNodeInfo}s for each active window
     */
    public static AccessibilityNodeInfo[] getWindowRoots() throws UIAException {
        List<AccessibilityNodeInfo> ret = BaseContext.getInstance().getDevice().getRoots();

        return ret.toArray(new AccessibilityNodeInfo[ret.size()]);
    }

//    public static synchronized AccessibilityNodeInfo currentActiveWindowRoot() {
//        if (currentActiveWindowRoot == null) {
//            refreshRootAXNode();
//        }
//        return currentActiveWindowRoot;
//    }
}
