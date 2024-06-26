package com.testerkit.uia.handlers.input;

import com.testerkit.common.utils.StringUtil;
import com.testerkit.uia.BaseContext;
import com.testerkit.common.log.Logger;

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
        String type = step.getNode().getType();
        if(StringUtil.isNotEmpty(type)&&type.equals("clipboard")){
            Logger.iFunc(FUNC,String.format("input text=%s",text));
            return BaseContext.getInstance().getDevice().typeFromClipBoard(text);
        }

        Logger.iFunc(FUNC,String.format("input text=%s",text));
        return BaseContext.getInstance().getDevice().type(text);
    }


}
