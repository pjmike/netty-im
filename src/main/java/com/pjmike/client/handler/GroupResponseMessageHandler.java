package com.pjmike.client.handler;

import com.pjmike.protocol.response.GroupResponseMessagePacket;
import com.pjmike.connection.Connection;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/09
 */
public class GroupResponseMessageHandler extends SimpleChannelInboundHandler<GroupResponseMessagePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupResponseMessagePacket msg) throws Exception {
        Connection fromUser = msg.getFromUser();
        String groupId = msg.getGroupId();
        String message = msg.getMessage();
        System.out.printf("收到群组 %s 中 %s 发来的消息：%s\n", groupId, fromUser.getUsername(), message);
    }
}
