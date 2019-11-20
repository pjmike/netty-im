package com.pjmike.server.handler;

import com.pjmike.protocol.codec.PacketCodec;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/07
 */
public class ServerHandlerInitializer extends ChannelInitializer<SocketChannel> {
    private static MetricHandler metricHandler = new MetricHandler();
    @Override
    protected void initChannel(SocketChannel sc) {
        sc.pipeline()
                .addLast(new LoggingHandler(LogLevel.DEBUG))
                .addLast(new PacketCodec())
                .addLast(new LoginRequestHandler())
                .addLast(new AuthHandler())
                .addLast(new MessageRequestHandler())
                .addLast(new RequestHandler())
                .addLast(new CreateGroupRequestHandler())
                .addLast(new GroupRequestMessageHandler())
                .addLast(metricHandler)
                .addLast(new LogoutRequestHandler());

    }
}
