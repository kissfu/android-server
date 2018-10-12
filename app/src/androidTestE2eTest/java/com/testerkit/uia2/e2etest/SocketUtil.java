package com.testerkit.uia2.e2etest;



import com.testerkit.uia.utils.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by able on 2017/7/11.
 */
public class SocketUtil {



    public static ResultInfo request(String ip,int port,String command,int timeout){
        ResultInfo resultInfo = new ResultInfo();
        Socket socket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        InputStreamReader input = null;
        try {
            socket = new Socket(ip, port);
            socket.setSoTimeout(timeout);
            input = new InputStreamReader(socket.getInputStream(),"UTF-8");
            reader = new BufferedReader(input);
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            // 向socket server发送指令。
            if (command != null && command.length() > 0) {
                writer.println(command);
                writer.flush();
            }
            // 获取Socket Server的响应。
            String line = reader.readLine();
            if (line != null) {
                resultInfo.setDetail(line.trim());
            }
        }catch (Exception e) {
            Logger.error("Socket Error: " + e.getMessage(), e);
            resultInfo.setStateResult(StateResultEnum.FAILED);
            resultInfo.setDetail("Socket Error"+e.getMessage());
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception ignored) {
                Logger.error("Socket finally Error: " + ignored.getMessage(), ignored);
            }
        }
        return resultInfo;
    }
}
