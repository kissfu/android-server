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

import com.testerkit.uia.BaseContext;
import com.testerkit.uia.interfaces.ITestCase;
import com.testerkit.uia.requests.socket.FullSocketRequest;
import com.testerkit.uia.utils.Constants;
import com.testerkit.uia.utils.Logger;
import com.testerkit.uia.utils.SocketUtils;
import com.testerkit.uia2.core.DeviceCore2;

import org.junit.Test;


//@SuppressWarnings("JavaDoc")
public class DeviceCommandsTest extends BaseTest implements ITestCase {


    /**
     * 坐标 点下去和抬上去
     */
    @Test
    public void touchDownAndUp(){
        initCore();

        String command = super.getAssets("touch-down.json");

        FullSocketRequest fullSocketRequest = new FullSocketRequest(command);
        String resultInfo = SocketUtils.request(Config.HOST,Config.PORT,command,5*60*1000);
        command = super.getAssets("touch-up.json");
        resultInfo = SocketUtils.request(Config.HOST,Config.PORT,command,5*60*1000);
        //Logger.info(resultInfo.getDetail());
    }
    /**
     * 界面元素获取
     */
    @Test
    public void getSource() {
        initCore();

        String command = super.getAssets("source.json");

        String resultInfo = SocketUtils.request(Config.HOST,Config.PORT,command,5*60*1000);
        Logger.debug("===>",resultInfo);

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

        String resultInfo = SocketUtils.request(Config.HOST,Config.PORT,command,5*60*1000);
        Logger.debug("===>",resultInfo);


    }

    @Override
    public void initCore() {
        Constants.PRO = "2";
        UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        BaseContext.getInstance().setTestCase(this);
        BaseContext.getInstance().setDevice( new DeviceCore2(uiDevice));

    }

}
