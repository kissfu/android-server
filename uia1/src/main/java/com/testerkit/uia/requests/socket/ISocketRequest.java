package com.testerkit.uia.requests.socket;

import com.testerkit.common.json.StepJson;
import com.testerkit.uia.requests.IRequest;

import java.util.Map;

public interface ISocketRequest extends IRequest {
    StepJson getStepInfo();
}
