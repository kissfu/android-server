package com.testerkit.uia.servers.socket;

import android.util.Log;

import com.testerkit.uia.requests.socket.FullSocketRequest;
import com.testerkit.uia.requests.socket.FullSocketResponse;
import com.testerkit.uia.requests.socket.ISocketRequest;
import com.testerkit.uia.requests.socket.ISocketResponse;
import com.testerkit.uia.requests.socket.impl.NettySocketRequest;
import com.testerkit.uia.requests.socket.impl.NettySocketResponse;
import com.testerkit.uia.servers.IServlet;
import com.testerkit.uia.utils.Logger;

import java.util.List;
import java.util.logging.Level;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by able on 2017/12/5.
 */

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private final static java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(NettyServerHandler.class.getName());

    private List<ISocketServlet> servletHandlers;

    public NettyServerHandler(List<ISocketServlet> servletHandlers) {
        this.servletHandlers = servletHandlers;
    }

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //解析请求的msg
        FullSocketRequest request = new FullSocketRequest();

        //初始化返回类
        FullSocketResponse response = new FullSocketResponse();

        ISocketRequest socketRequest = new NettySocketRequest(request);
        ISocketResponse socketResponse = new NettySocketResponse(response);

        for (ISocketServlet servlet : servletHandlers){
            servlet.handleSocketRequest(socketRequest,socketResponse);
            if(socketResponse.isClosed()){
                break;
            }
        }

        if (!socketResponse.isClosed()) {
            socketResponse.setStatus(404);
            socketResponse.end();
        }

        ctx.write(response).addListener(ChannelFutureListener.CLOSE);
        super.channelRead(ctx, msg);


//        String input = new String(((String)msg).getBytes(),"UTF-8");
//        Log.i(Utils.tag, String.format("【server receive %s】:",++counter)+input);
//        MessageHandler messageHandler = new MessageHandler();
//        byte[] respBytes = messageHandler.handler(input);
//        byte[] numBytes = ByteUtil.intToByteArray(respBytes.length);
//        byte[] totalBytes = ByteUtil.byteMerger(numBytes,respBytes);
//        ByteBuf resp = Unpooled.copiedBuffer(totalBytes);
//        ctx.writeAndFlush(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        ctx.fireChannelReadComplete();
        Logger.info( "server", " ReadComplete:");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.log(Level.SEVERE, "Error handling request", cause);
        ctx.close();
        super.exceptionCaught(ctx, cause);
    }
}
