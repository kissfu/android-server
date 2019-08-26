package com.testerkit.uia.core;

import android.view.accessibility.AccessibilityNodeInfo;

import com.testerkit.common.enums.UIAType;
import com.testerkit.common.exceptions.UIAException;
import com.testerkit.uia.BaseContext;

import static com.testerkit.common.utils.ReflectionUtil.invoke;
import static com.testerkit.common.utils.ReflectionUtil.method;

/**
 * Created by able on 2018/2/11.
 */

public abstract class QueryController {

    public abstract String CLASS_QUERY_CONTROLLER();
    private static final String METHOD_GET_ROOT_NODE = "getRootNode";
    private static final String METHOD_GET_ACCESSIBILITY_ROOT_NODE = "getAccessibilityRootNode";

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
        UIAType type = BaseContext.getInstance().getDevice().type;
        if(type == UIAType.UIA1_IN_HIGH_LEVEL){
           return  (AccessibilityNodeInfo) invoke(queryController,METHOD_GET_ACCESSIBILITY_ROOT_NODE);
        }
        return (AccessibilityNodeInfo) invoke(method(CLASS_QUERY_CONTROLLER(), METHOD_GET_ROOT_NODE), queryController);
    }

}