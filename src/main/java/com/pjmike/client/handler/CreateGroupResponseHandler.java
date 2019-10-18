package com.pjmike.client.handler;

import com.pjmike.protocol.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/09
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket msg) throws Exception {
        System.out.printf("群创建成功，群名为：%s，群ID为：%s，当前群成员有：%s\n",msg.getGroupName(),msg.getGroupId(),msg.getUserNameList());
    }
}
