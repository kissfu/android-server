package com.testerkit.uia1.e2etest;

import com.testerkit.uia.servers.socket.NettyServer;
import com.testerkit.uia.utils.FileUtil;
import com.testerkit.uia.utils.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;

public abstract class BaseTest {

    protected String FUNC = "uia1 unit";
    private String runSh = "/Users/able/Desktop/workspace/mycode/githubs/testerkit/android-server/uia1/run.sh";
    @Before
    public void setup(){
        Logger.DEBUG_LOCAL = 1;
        Logger.iFunc(FUNC,"===>setup");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    ProcessBuilder pb = new ProcessBuilder(runSh , " uiautomator");
                    pb.directory(new File(runSh).getParentFile());
                    Process p= pb.start(); //Runtime.getRuntime().exec(runSh + " uiautomator");
                    BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line = null;
                    while ((line = in.readLine()) != null) {
                        Logger.iFunc(FUNC,line+"\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @After
    public void tearDown(){
        Logger.iFunc(FUNC,"===>tearDown");

    }

    //System.getProperty("user.dir")

    protected String getAssets(String fileName){
        try {
            String dir = FileUtil.getPath(System.getProperty("user.dir"),"src","androidTest","assets",fileName);
            FileInputStream fileInputStream = new FileInputStream(dir);
            InputStreamReader isr = new InputStreamReader(fileInputStream,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            StringBuilder builder = new StringBuilder();
            while((line = br.readLine()) != null){
                builder.append(line);
            }
            br.close();
            isr.close();
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
