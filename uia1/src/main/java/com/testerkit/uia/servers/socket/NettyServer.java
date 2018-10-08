package com.testerkit.uia.servers.socket;

import android.util.Log;
//import cn.testin.utils.Logger;
//import cn.testin.utils.Utils;
import com.testerkit.uia.servers.IServlet;
import com.testerkit.uia.servers.ServerBase;
import com.testerkit.uia.utils.Logger;

import java.util.ArrayList;
import java.util.List;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by able on 2017/12/5.
 */

public class NettyServer extends ServerBase {


    protected final List<ISocketServlet> servlets = new ArrayList<ISocketServlet>();

    private int VIEW_SERVER_DEFAULT_PORT = 20008;

    public void bindTry(){
        try {
            VIEW_SERVER_DEFAULT_PORT = 20008;
            for (int i = 0; i < 10; i++) {
               if(bind(VIEW_SERVER_DEFAULT_PORT)) {
                   break;
               }
                VIEW_SERVER_DEFAULT_PORT++;
            }
        }catch (Exception e){
            Logger.error(e.getMessage(), e);
        }finally {
            //MyContext.isRunning = false;
        }
    }

    EventLoopGroup bossGroup;
    EventLoopGroup workerGroup;
    public boolean bind(int port) throws Exception{
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.option(ChannelOption.SO_BACKLOG,1024);
            b.handler(new LoggingHandler(LogLevel.INFO));
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {

                    //region 1. LineBasedFrameDecoder + StringDecoder
                    /**
                     * 1。接收字符串长度超过最大长度，如果超出则抛出异常
                     * 2。以"\n"或者"\r\n"作为结束符，作为一行数据。
                     * 3。StringDecoder接受的字节转化为字符串
                     */
                    ch.pipeline().addLast(new LineBasedFrameDecoder(1024 * 512));
                    ch.pipeline().addLast(new StringDecoder());
                    //endregion
                    servlets.clear();
                    servlets.add(new NettyServlet());
                    ch.pipeline().addLast(new NettyServerHandler(servlets));
                }
            });
            Logger.info("server","binding...");
            //绑定端口，同步等待成功
            ChannelFuture f = b.bind(port).sync();
            //Logger.onRecorderServerPortChange(port);
            Logger.info("server","binding... is ok! then sync...");
            //等待服务端监听端口关闭
            f.channel().closeFuture().sync();
            Logger.info("server","sync... is over!");
        }catch (Exception e){
            Logger.error(e.getMessage(), e);
            return false;
        }finally {
            close();
        }
        //执行完不再挂起，server停止
        return true;
    }

    public void close(){
        if(bossGroup != null && workerGroup != null) {
            //优雅退出，四方线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            Logger.info("server", "shutdownGracefully");
        }
    }

    @Override
    public void start() {
        bindTry();
    }

    @Override
    public void stop() {
        close();
    }

    /**
     * Java单例模式的写法（Initialization on Demand Holder模式）
     * http://blog.csdn.net/kohaku/article/details/39268697
     * http://www.cnblogs.com/sunxucool/p/3949327.html
     */
    private static class SingletonHolder
    {
        public final static NettyServer instance = new NettyServer();
    }
    public static NettyServer getInstance()
    {
        return SingletonHolder.instance;
    }

}
