package com.testerkit.common.utils;

import com.testerkit.common.log.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketUtil {
    static String FUNC= "socket";
    /**
     * @param ipAddress adb地址映射
     * @param command   命令
     * @param port      手机端的UIAutomator端口
     * @param timeout   超时时间设置 单位：毫秒
     * @return 执行结果 json格式字符串
     */
    public synchronized static String request(String ipAddress, int port, String command, int timeout) {
        Socket socket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        InputStreamReader input = null;
        InputStream stream = null;
        OutputStreamWriter outputStream = null;

        try {
            socket = new Socket();
            socket.setSoTimeout(timeout);
            socket.connect(new InetSocketAddress(ipAddress, port), 5000);
            stream = socket.getInputStream();
            input = new InputStreamReader(stream, "UTF-8");
            reader = new BufferedReader(input);
            outputStream = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            writer = new PrintWriter(outputStream);

            // 向socket server发送指令。
            if (command != null && command.length() > 0) {
                writer.println(command);//包括发送"\n"
                writer.flush();
            }
            byte[] reply = new byte[4];
            stream.read(reply);
            int len = ByteUtil.byteArrayToInt(reply);
            byte[] bytesReceived = new byte[len];
            int counter = 0;
            do {
                // 当UIA被kill后，read会立即返回-1，并不抛出异常
                int bytes = stream.read(bytesReceived, counter, bytesReceived.length - counter);
                counter = counter + bytes;
                if (counter >= len) {
                    break;
                }
            } while (counter > 0);
            if(counter > 0) {
                return new String(bytesReceived, "UTF-8");
            }
        } catch (Exception e) {
            Logger.error( "request error: " + e.getMessage(), e);
            //throw e;

        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (Exception e) {
                Logger.error( e.getMessage());
            }
            try {
                if (input != null) {
                    input.close();
                }
            } catch (Exception e) {
                Logger.error( e.getMessage(), e);
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                Logger.error( e.getMessage(), e);
            }

            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                Logger.error(e.getMessage(), e);
            }
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e) {
                Logger.error( e.getMessage(), e);
            }
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
                Logger.error( e.getMessage(), e);
            }
        }

        return null;
    }

}
