package com.testerkit.uia1.core;

import android.view.accessibility.AccessibilityNodeInfo;

import com.testerkit.uia.core.QueryController;
import com.testerkit.uia.exceptions.UIAException;
import com.testerkit.uia.utils.Logger;

import static com.testerkit.uia.utils.ReflectionUtils.invoke;
import static com.testerkit.uia.utils.ReflectionUtils.method;

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