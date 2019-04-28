package com.testerkit.common.constants;


import com.testerkit.common.enums.StepAction;
import com.testerkit.common.enums.StepRule;

public class ConstantStep {

    public static final String ACTION_PARENNT = StepAction.ACTION_PARENT.getAction();

    public static final String FIND_CLICK = StepAction.FIND.getAction() + "/" + StepRule.CLICK.getRule();

    public static final String SOURCE_CLASS = StepAction.SOURCE.getAction() + "/" + StepRule.CLASS.getRule();
    public static final String SOURCE_NODE = StepAction.SOURCE.getAction() + "/" + StepRule.NODE.getRule();


    public static final String INPUT_DEFAULT = StepAction.INPUT.getAction() + "/" + StepRule.DEFAULT.getRule();

    public static final String PRESS_KEY = StepAction.PRESS.getAction() + "/" + StepRule.KEY.getRule();
    public static final String PRESS_KEY_NAME = StepAction.PRESS.getAction() + "/" + StepRule.KEY_NAME.getRule();


    public static final String TOUCH_DOWN = StepAction.TOUCH.getAction() + "/" + StepRule.DOWN.getRule();
    public static final String TOUCH_UP = StepAction.TOUCH.getAction() + "/" + StepRule.UP.getRule();
    public static final String TOUCH_MOVE = StepAction.TOUCH.getAction() + "/" + StepRule.MOVE.getRule();
    public static final String TOUCH_CLICK = StepAction.TOUCH.getAction() + "/" + StepRule.CLICK.getRule();


}
