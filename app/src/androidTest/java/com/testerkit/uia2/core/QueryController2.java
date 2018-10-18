package com.testerkit.uia2.core;

import com.testerkit.uia.core.QueryController;

/**
 * Created by able on 2018/2/11.
 */

public class QueryController2 extends QueryController{


    public QueryController2(Object queryController) {
        super(queryController);
    }

    @Override
    public String CLASS_QUERY_CONTROLLER() {
        return "android.support.test.uiautomator.QueryController";
    }


}