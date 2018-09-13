package com.testerkit.uia.core;

import android.view.accessibility.AccessibilityNodeInfo;


import com.testerkit.uia.exceptions.UIAException;
import com.testerkit.uia.utils.Logger;

import static com.testerkit.uia.utils.ReflectionUtils.invoke;
import static com.testerkit.uia.utils.ReflectionUtils.method;

/**
 * Created by able on 2018/2/11.
 */

public abstract class QueryController {

    public abstract String CLASS_QUERY_CONTROLLER();
    private static final String METHOD_GET_ACCESSIBILITY_ROOT_NODE = "getRootNode";

    private final Object queryController;

    public QueryController(Object queryController) {
        this.queryController = queryController;
    }

    /**
     * Gets the root node from accessibility and if it fails to get one it will
     * retry every 250ms for up to 1000ms.
     * @return null if no root node is obtained
     */

    public AccessibilityNodeInfo getAccessibilityRootNode() throws UIAException {
        return (AccessibilityNodeInfo) invoke(method(CLASS_QUERY_CONTROLLER(), METHOD_GET_ACCESSIBILITY_ROOT_NODE), queryController);
    }

}