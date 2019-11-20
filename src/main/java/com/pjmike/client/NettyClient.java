package com.pjmike.client;

import com.pjmike.client.handler.ClientHandlerInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/06
 */
@Slf4j
public class NettyClient {
    private static int port = 8888;
    private static String host = "127.0.0.1";
    private int MAX_RETRY = 5;
    public Channel channel = null;

    public void start() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                //客户端连接服务端最大允许时间，5s
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ClientHandlerInitializer());
        connect(bootstrap, host, port, MAX_RETRY);
    }

    public void connect(Bootstrap bootstrap, String host, int port, int retry) {
        ChannelFuture channelFuture = bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                log.info("连接服务端成功");
            } else if (retry == 0) {
                log.error("重试次数已用完，放弃连接");
            } else {
                //第几次重连：
                int order = (MAX_RETRY - retry) + 1;
                //本次重连的间隔
                int delay = 1 << order;
                log.error("{} : 连接失败，第 {} 重连....", new Date(), order);
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
        channel = channelFuture.channel();
    }
}
