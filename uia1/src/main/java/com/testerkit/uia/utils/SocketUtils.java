package com.testerkit.uia.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketUtils {
    static String FUNC= "socket";
    public static String request(String ip,int port,String command,int timeout){
        String line="";
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
            //line = reader.readLine();
        }catch (Exception e) {
            Logger.error("Socket Error: " + e.getMessage(), e);
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
        return line;
    }

    /**
     * 发送socket请求
     * @param clientIp
     * @param clientPort
     * @param msg
     * @return
     */
//    public static synchronized String tcpPost(String clientIp,String clientPort,String msg){
//        String rs = "";
//
//        if(clientIp==null||"".equals(clientIp)||clientPort==null||"".equals(clientPort)){
//            Logger.error("Ip或端口不存在...");
//            return null;
//        }
//
//        int clientPortInt = Integer.parseInt(clientPort);
//
//        Logger.info(FUNC,"clientIp："+clientIp+" clientPort："+clientPort);
//
//        Socket s = null;
//        OutputStream out = null;
//        InputStream in = null;
//        try {
//            s = new Socket(clientIp, clientPortInt);
//            s.setSendBufferSize(4096);
//            s.setTcpNoDelay(true);
//            s.setSoTimeout(60*1000);
//            s.setKeepAlive(true);
//            out = s.getOutputStream();
//            in = s.getInputStream();
//
//            //准备报文msg
//            Logger.info(FUNC,"准备发送报文："+msg);
//
//            out.write(msg.getBytes("utf-8"));
//            out.flush();
//
////            byte[] rsByte = readStream(in);
////
////            if(rsByte!=null){
////                rs = new String(rsByte, "utf-8");
////            }
//
//
//        } catch (Exception e) {
//            Logger.error("tcpPost发送请求异常："+e.getMessage());
//        }finally{
//            Logger.info(FUNC,"tcpPost(rs)："+rs);
//            try {
//                if(out!=null){
//                    out.close();
//                    out = null;
//                }
//                if(in!=null){
//                    in.close();
//                    in = null;
//                }
//                if(s!=null){
//                    s.close();
//                    s = null;
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return rs;
//
//    }
//
//    /**
//     * 读取输入流
//     * @param in
//     * @return
//     */
//    private static byte[] readStream(InputStream in){
//        if(in==null){
//            return null;
//        }
//
//        byte[] b = null;
//        ByteArrayOutputStream outSteam = null;
//        try {
//            byte[] buffer = new byte[1024];
//            outSteam = new ByteArrayOutputStream();
//
//            int len = -1;
//            while ((len = in.read(buffer)) != -1) {
//                outSteam.write(buffer, 0, len);
//            }
//
//            b = outSteam.toByteArray();
//        } catch (IOException e) {
//            Logger.error("读取流信息异常"+e);
//            e.printStackTrace();
//        } finally{
//            try {
//                if(outSteam!=null){
//                    outSteam.close();
//                    outSteam = null;
//                }
//                if(in!=null){
//                    in.close();
//                    in = null;
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return b;
//    }
}
