package com.pjmike.server;

import com.pjmike.server.handler.ServerHandlerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/07
 */
@Slf4j
public class NettyServer {
    private final int port;

    public NettyServer(int port) {
        this.port = port;
    }

    public void start() {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.TCP_NODELAY, true)
                //最大的等待连接数量，默认是128，可以调整大一点为1024
                //使用方式: javaChannel().bind(localAddress,config.getBacklog());
                .option(ChannelOption.SO_BACKLOG, 1024)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ServerHandlerInitializer());
        bind(bootstrap, port);
    }

    public void bind(final ServerBootstrap bootstrap, final int port) {
        bootstrap.bind(port)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        log.info("端口 {} 绑定成功,服务端启动成功", port);
                    } else {
                        log.warn("端口 {} 绑定失败,服务端启动失败", port);
                    }
                });
    }
}
