package com.testerkit.uia.requests.socket;

import com.testerkit.common.json.StepJson;
import com.testerkit.uia.requests.IRequest;

public interface ISocketRequest extends IRequest {
    StepJson getStepInfo();
    String getRawMsg();
}
