package com.testerkit.uia1.e2etest;

import com.testerkit.uia.utils.Logger;
import com.testerkit.uia.utils.SleepUtils;
import com.testerkit.uia.utils.SocketUtils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeviceCommandsTest extends BaseTest {

    /**
     * 按键操作
     */
    @Test
    public void inputText() {


        String command = super.getAssets("input-default.json");

        String resultInfo = SocketUtils.request(Config.HOST,Config.PORT,command,5*60*1000);
        Logger.debug("===>",resultInfo);
    }
}
