package com.testerkit.uia.handlers.input;

import com.testerkit.common.utils.StringUtil;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.utils.Logger;

public class InputDefault extends InputEvent {
    public InputDefault(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected boolean executeInputEvent() throws Exception {

        String text = step.getNode().getText();
        if(StringUtil.isNullOrEmpty(text)){
            return false;
        }
        Logger.iFunc(FUNC,String.format("input text=%s",text));
        return BaseContext.getInstance().getDevice().type(text);

    }


}
