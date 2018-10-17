package com.testerkit.screen;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;

public class ClientHelper {
    LocalSocket mSocket;
    OutputStream mOut;
    String TAG="WebRtcClient-write";
    boolean connect() {
        //创建socket
        mSocket = new LocalSocket();

        //设置连接地址
        LocalSocketAddress address = new LocalSocketAddress("minitouch", LocalSocketAddress.Namespace.ABSTRACT);

        //建立连接
        try {
            mSocket.connect(address);
            //获取数据输入流 可以读数据
            //mIn = mSocket.getInputStream();

            //获取数据输出流 可以写数据
            mOut = mSocket.getOutputStream();

            Log.i(TAG,"链接成功，"+ mSocket.isConnected());
        } catch (Exception e) {
            Log.i(TAG,e.getMessage());
        }

       return true;
    }


    private static class Singleton{
      private static   ClientHelper clientHelper = new ClientHelper();
    }


    public static ClientHelper getInstance(){
        return Singleton.clientHelper;
    }

    public void write(String msg){
        try {
            if(mSocket == null || mSocket.isConnected() == false){
                close();
                connect();
            }
            if(mOut == null){
                return;
            }
            mOut.write(msg.getBytes());
        } catch (Exception e) {
            Log.i(TAG,e.getMessage());
        }
    }

    void close(){
        try {
            if(mOut == null){
                return ;
            }
            mOut.close();
        } catch (IOException e) {
            Log.i(TAG,e.getMessage());
        }
    }


}
