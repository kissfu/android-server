package com.testerkit.common.utils;

public class StopWatch {

    private long startTime = System.currentTimeMillis();

    public void restart(){
        startTime = System.currentTimeMillis();
    }

    public String  toElapsedMS(){
        return String.format("[elapsed %s ms]",System.currentTimeMillis() - startTime);
    }
    public long getElapsedMS(){
        return System.currentTimeMillis() - startTime;
    }
    public String  toElapsedMSAndResart(){
        String result = String.format("[elapsed %s ms]",System.currentTimeMillis() - startTime);
        restart();
        return result;
    }
}
