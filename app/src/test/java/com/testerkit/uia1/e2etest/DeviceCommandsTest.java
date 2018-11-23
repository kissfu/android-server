package com.testerkit.uia1.e2etest;

import com.testerkit.uia.model.serach.StepInfo;
import com.testerkit.uia.requests.socket.FullSocketRequest;
import com.testerkit.uia.utils.Logger;
import com.testerkit.uia.utils.SleepUtils;
import com.testerkit.uia.utils.SocketUtils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeviceCommandsTest extends BaseTest {


    @Override
    protected boolean needServer() {
        return false;
    }

    /**
     * 按键操作
     */
    @Test
    public void inputText() {


        String command = super.getAssets("input-default.json");

        String resultInfo = SocketUtils.request(Config.HOST, Config.PORT, command, 5 * 60 * 1000);
        Logger.info("===>", resultInfo);
    }

    @Test
    public void findClick() {

        String command = super.getAssets("find-click.json");

        FullSocketRequest request = new FullSocketRequest(command);


//        String resultInfo = SocketUtils.request(Config.HOST,Config.PORT,command,5*60*1000);
//        Logger.debug("===>",resultInfo);
    }


    @Test
    public void parseStep() {

        String value = null;

        System.out.println(value + "");
//        String msg = super.getAssets("find-click.json");
//        StepInfo step = new StepInfo();
//        step.parse(msg);


    }


}
