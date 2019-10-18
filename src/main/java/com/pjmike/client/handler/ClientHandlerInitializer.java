package com.pjmike.client.handler;

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
public class ClientHandlerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel sh) {
        sh.pipeline()
//                .addLast(new LoggingHandler(LogLevel.INFO))
                .addLast(new PacketCodec())
                .addLast(new LoginResponseHandler())
                .addLast(new MessageResponseHandler())
                .addLast(new CreateGroupResponseHandler())
                .addLast(new GroupResponseMessageHandler());
    }
}
