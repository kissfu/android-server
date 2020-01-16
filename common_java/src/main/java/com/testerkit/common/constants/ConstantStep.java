package com.testerkit.common.constants;


import com.testerkit.common.enums.StepAction;
import com.testerkit.common.enums.StepRule;

/**
 * 手机指令和步骤指令
 */
public class ConstantStep {

    public static final String ACTION_PARENNT = StepAction.ACTION_PARENT.getAction();

    // 查找选中的元素，类似下拉框，活着其他
    public static final String FIND_SELECTION = StepAction.FIND.getAction() + "/" + StepRule.SELECTION.getRule();
    //find element
    public static final String FIND_NODE = StepAction.FIND.getAction() + "/" + StepRule.NODE.getRule();
    public static final String FIND_CLICK = StepAction.FIND.getAction() + "/" + StepRule.CLICK.getRule();
    public static final String FIND_ASSERT = StepAction.FIND.getAction() + "/" + StepRule.ASSERT.getRule();
    public static final String FIND_INPUT = StepAction.FIND.getAction() + "/" + StepRule.INPUT.getRule();

    public static final String SOURCE_CLASS = StepAction.SOURCE.getAction() + "/" + StepRule.CLASS.getRule();
    public static final String SOURCE_NODE = StepAction.SOURCE.getAction() + "/" + StepRule.NODE.getRule();

    // 输入步骤
    public static final String INPUT_DEFAULT = StepAction.INPUT.getAction() + "/" + StepRule.DEFAULT.getRule();
    public static final String INPUT_VALUE = StepAction.INPUT.getAction() + "/" + StepRule.VALUE.getRule();

    public static final String PRESS_KEY = StepAction.PRESS.getAction() + "/" + StepRule.KEY.getRule();
    public static final String PRESS_KEY_NAME = StepAction.PRESS.getAction() + "/" + StepRule.KEY_NAME.getRule();


    // touch
    public static final String TOUCH_DOWN = StepAction.TOUCH.getAction() + "/" + StepRule.DOWN.getRule();
    public static final String TOUCH_UP = StepAction.TOUCH.getAction() + "/" + StepRule.UP.getRule();
    public static final String TOUCH_MOVE = StepAction.TOUCH.getAction() + "/" + StepRule.MOVE.getRule();
    public static final String TOUCH_CLICK = StepAction.TOUCH.getAction() + "/" + StepRule.CLICK.getRule();
    public static final String TOUCH_POINTS= StepAction.TOUCH.getAction() + "/" + StepRule.POINTS.getRule();

    //screen
    public static final String SCREEN_SCREENSHOT= StepAction.SCREEN.getAction() + "/" + StepRule.SCREENSHOT.getRule();
    public static final String SCREEN_SWIPE= StepAction.SCREEN.getAction() + "/" + StepRule.SWIPE.getRule();

    //variable
    public static final String VARIABLE_ASSIGN= StepAction.VARIABLE.getAction() + "/" + StepRule.ASSIGN.getRule();
    public static final String VARIABLE_EXPRESSION= StepAction.VARIABLE.getAction() + "/" + StepRule.EXPRESSION.getRule();



    //系统app的一些接口
    public static final String APP_LIST = StepAction.APP.getAction() + "/" + StepRule.LIST.getRule();


    //while-ifelse
    public static final String IF_ELSE = StepAction.IF.getAction() + "/" + StepRule.ELSE.getRule();

    //sms
    public static final String SMS_VERIFICATION_CODE = StepAction.SMS.getAction() + "/" + StepRule.VERIFICATION_CODE.getRule();

    public static int TIMEOUT_DEFAULT = 15*1000;
}
