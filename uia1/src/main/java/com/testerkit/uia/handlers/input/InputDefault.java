package com.testerkit.uia.handlers.input;

import com.testerkit.uia.BaseContext;
import com.testerkit.uia.handlers.request.SafeRequestHandler;
import com.testerkit.uia.utils.StringUtils;

public class InputDefault extends InputEvent {
    public InputDefault(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected boolean executeInputEvent() throws Exception {

        String text = step.getNode().getText();
        if(StringUtils.isNullOrEmpty(text)){
            return false;
        }
        return BaseContext.getInstance().getDevice().type(text);

    }


}
