package com.pjmike.client.handler;

import com.pjmike.protocol.request.MessagePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/07
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessagePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessagePacket requestPacket) throws Exception {
        String fromUserId = requestPacket.getFromUserId();
        String message = requestPacket.getMessage();
        System.out.printf("收到ID：%s 发来的消息：%s\n", fromUserId, message);
    }
}
