package com.testerkit.uia1.core;

import android.view.accessibility.AccessibilityNodeInfo;

import com.testerkit.uia.core.QueryController;
import com.testerkit.common.exceptions.UIAException;
import com.testerkit.common.log.Logger;

import static com.testerkit.common.utils.ReflectionUtil.invoke;
import static com.testerkit.common.utils.ReflectionUtil.method;

/**
 * Created by able on 2018/2/11.
 */

public class QueryController1 extends QueryController{


    public QueryController1(Object queryController) {
        super(queryController);
    }

    @Override
    public String CLASS_QUERY_CONTROLLER() {
        return "com.android.uiautomator.core";
    }


}