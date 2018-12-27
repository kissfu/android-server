package com.testerkit.common.log;

import com.testerkit.common.utils.Constants;
import com.testerkit.common.utils.StringUtil;

public abstract class LogBase implements ILog {
    //[总标签][uia版本][功能]
    protected String TAG = Constants.TAG + "["+Constants.PRO + "." + Constants.VERSION + "]";

    protected  String getString(Object... args) {
        StringBuilder content = new StringBuilder();

        for (Object arg : args) {
            if (arg != null) {
                content.append(arg.toString());
            }
        }

        return content.toString();
    }

    @Override
    public void iFunc(String func, Object... messages) {
        if (StringUtil.isNullOrEmpty(func)) {
            info(messages);
        } else {
            info(String.format("[%s]", func),getString(messages));
        }
    }

}
