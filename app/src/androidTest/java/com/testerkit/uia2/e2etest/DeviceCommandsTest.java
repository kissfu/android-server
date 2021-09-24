/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.testerkit.uia2.e2etest;


import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;

import com.testerkit.common.enums.StepAction;
import com.testerkit.common.enums.StepRule;
import com.testerkit.common.json.StepJson;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.RectInfo;
import com.testerkit.common.utils.Constants;
import com.testerkit.common.utils.GsonUtil;
import com.testerkit.common.utils.SleepUtil;
import com.testerkit.common.utils.SocketUtil;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.handlers.find.FindSelection;
import com.testerkit.uia.interfaces.ITestCase;
import com.testerkit.uia.log.LogAndroid;
import com.testerkit.uia2.core.DeviceCore2;

import org.junit.Test;


//@SuppressWarnings("JavaDoc")
public class DeviceCommandsTest extends BaseTest implements ITestCase {

    private static String TAG = "test";

    @Test
    public void dump() {
        initCore();

        StepJson stepJson = new StepJson();
        stepJson.setAction(StepAction.SOURCE.getAction());
        stepJson.setRule(StepRule.NODE.getRule());
        String command = GsonUtil.gsonString(stepJson);
        SleepUtil.sleep(5);
        String resultInfo = SocketUtil.request(Config.HOST, Config.PORT, command, 5 * 60 * 1000);
        Logger.info("===>" + resultInfo);

    }


    /**
     * 坐标 点下去和抬上去
     */
    @Test
    public void touchDownAndUp() {
        initCore();

        String resultInfo = "";
        String command = "";

//        command = super.getAssets("touch-down.json");
//        resultInfo = SocketUtils.request(Config.HOST, Config.PORT, command, 5 * 60 * 1000);
//        Logger.info("===>" + resultInfo);
//
//
//        command = super.getAssets("touch-up.json");
//        resultInfo = SocketUtils.request(Config.HOST, Config.PORT, command, 5 * 60 * 1000);
//        Logger.info("===>" + resultInfo);

        command = super.getAssets("touch-click.json");
        resultInfo = SocketUtil.request(Config.HOST, Config.PORT, command, 5 * 60 * 1000);
        Logger.info("===>" + resultInfo);
    }

    /**
     * 界面元素获取
     */
    @Test
    public void getSource() {
        initCore();

        //耗费时间要长，以node的class名字为标签
//        String command = super.getAssets("source-class.json");
        //比较快，以node为标签，className为属性
        String command = super.getAssets("source-node.json");

        String resultInfo = null;
        try {
            resultInfo = SocketUtil.request(Config.HOST, Config.PORT, command, 5 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Logger.debug("result===>", resultInfo);

    }

    @Test
    public void getFindSelection() {
        initCore();

        //耗费时间要长，以node的class名字为标签
//        String command = super.getAssets("source-class.json");
        //比较快，以node为标签，className为属性
        String command = super.getAssets("find-selection.json");

        String resultInfo = null;
        try {
            resultInfo = SocketUtil.request(Config.HOST, Config.PORT, command, 5 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Logger.debug("result===>", resultInfo);

    }

    /**
     * 按键操作
     */
    @Test
    public void pressKey() {
        initCore();

//        String command = super.getAssets("press-key_name.json");
//        String command = super.getAssets("press-key_code.json");
        String command = super.getAssets("press-key_code_long.json");

        String resultInfo = SocketUtil.request(Config.HOST, Config.PORT, command, 5 * 60 * 1000);
        Logger.debug("===>", resultInfo);
    }

    /**
     * 按键操作
     */
    @Test
    public void inputText() {
        initCore();

        String command = super.getAssets("input-default.json");

        String resultInfo = SocketUtil.request(Config.HOST, Config.PORT, command, 5 * 60 * 1000);
        Logger.debug("===>", resultInfo);
    }


    @Test
    public void findClick() {
        initCore();

        String command = super.getAssets("find-click.json");
        command = "{\"action\":\"find\",\"rule\":\"click\",\"condition\":{\"relation\":\"AND\",\"name\":{\"option\":\"REQUIRED\",\"relation\":\"AND\",\"arr\":[\"icon_icon\"]},\"clazz\":{\"option\":\"FILTER\",\"relation\":\"AND\",\"arr\":[\"android.widget.ImageView\"]},\"packageName\":{\"option\":\"FILTER\",\"relation\":\"AND\",\"arr\":[\"com.miui.home\"]},\"xpath\":{\"xps\":[{\"xpath\":\"//node[@content-desc\\u003d\\u0027音乐 7 个未读\\u0027]/node[@name\\u003d\\u0027icon_container\\u0027 and @class\\u003d\\u0027android.widget.FrameLayout\\u0027 and @index\\u003d\\u00270\\u0027]/node[@class\\u003d\\u0027android.widget.FrameLayout\\u0027 and @index\\u003d\\u00270\\u0027]/node[@name\\u003d\\u0027icon_icon\\u0027 and @class\\u003d\\u0027android.widget.ImageView\\u0027 and @index\\u003d\\u00270\\u0027]\",\"option\":\"ALL\"}],\"option\":\"FILTER\",\"relation\":\"AND\",\"arr\":[]}}}";

        String resultInfo = SocketUtil.request(Config.HOST, Config.PORT, command, 5 * 60 * 1000);
        Logger.debug("===>", resultInfo);
    }

    @Test
    public void appList() {
        initCore();

        String command = super.getAssets("app-list.json");
        String resultInfo = SocketUtil.request(Config.HOST, Config.PORT, command, 5 * 60 * 1000);
        System.out.printf(resultInfo);
        Logger.debug("===>", resultInfo);
    }

    @Override
    public void initCore() {
        Constants.PRO = "2";
        Logger.addLog(new LogAndroid());
        UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        BaseContext.getInstance().setTestCase(this);
        BaseContext.getInstance().setDevice(new DeviceCore2(uiDevice));

    }


    @Test
    public void swipe() {
        initCore();

        SleepUtil.sleep(5);
        RectInfo rect = new RectInfo(0,1401,1080,1920);

        FindSelection selection = new FindSelection("");
        selection.swipe(rect,"一年",1);



    }

    @Test
    public void screenSwipe() {
        initCore();

        SleepUtil.sleep(5);
        String command = super.getAssets("screen-swipe.json");
        String resultInfo = SocketUtil.request(Config.HOST, Config.PORT, command, 5 * 60 * 1000);
        System.out.printf(resultInfo);
        Logger.debug("===>", resultInfo);



    }


}
