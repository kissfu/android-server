package com.testerkit.logcat;

import android.os.Process;

import com.testerkit.uia.utils.Logger;

import java.io.BufferedReader;

import java.io.InputStreamReader;

public class LogcatHelper {
    private  String FUNC = "logcat";
    private ILogcat logcat;

    private static class Singleton{
        private static   LogcatHelper logcatHelper = new LogcatHelper();
    }

    public static LogcatHelper getInstance(){
        return Singleton.logcatHelper;
    }


    public void stop(){

        try {
            if(p != null){
                p.destroy();
                //Process.killProcess(p.exitValue());
                p = null;
            }
        }catch (Exception e){
            Logger.error(e);
        }
    }
    java.lang.Process p;
    public void start(String cmd){
        stop();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    //Process p = Runtime.getRuntime().exec("/data/local/tmp/minitouch");
                    p = Runtime.getRuntime().exec("logcat -v time " + cmd);
                    BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line = null;
                    while ((line = in.readLine()) != null) {
                        Logger.info(FUNC,line+"\n");
                        if(logcat != null){
                            logcat.onLocat(line+"\n");
                        }
                        // s += line + "/n";
                    }
                } catch (Exception e) {
                    Logger.error(e);
                }
            }
        }).start();
    }

    public void setLogcat(ILogcat logcat) {
        this.logcat = logcat;
    }
}
