package com.testerkit.uia1.e2etest;

import com.testerkit.common.utils.SocketUtil;
import com.testerkit.uia.requests.socket.FullSocketRequest;
import com.testerkit.common.log.Logger;

import org.junit.Test;


public class DeviceCommandsTest extends BaseTest {


    @Override
    protected boolean needServer() {
        return true;
    }

    /**
     * 按键操作
     */
    @Test
    public void inputText() {


        String command = super.getAssets("input-default.json");

        String resultInfo = SocketUtil.request(Config.HOST, Config.PORT, command, 5 * 60 * 1000);
        Logger.info("===>", resultInfo);
    }

    @Test
    public void findClick() {

        String command = super.getAssets("find-click.json");

        FullSocketRequest request = new FullSocketRequest(command);


//        String resultInfo = SocketUtils.request(Config.HOST,Config.PORT,command,5*60*1000);
//        Logger.debug("===>",resultInfo);
    }

    /**
     * 界面元素获取
     */
    @Test
    public void getSource() {

        //耗费时间要长，以node的class名字为标签
//        String command = super.getAssets("source-class.json");
        //比较快，以node为标签，className为属性
        String command = super.getAssets("source-node.json");

        String resultInfo = SocketUtil.request(Config.HOST,Config.PORT,command,5*60*1000);
        Logger.debug("===>",resultInfo);

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
