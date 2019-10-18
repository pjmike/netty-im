package com.pjmike.server.handler;

import com.alibaba.fastjson.JSON;
import com.pjmike.protocol.request.GroupRequestMessagePacket;
import com.pjmike.protocol.response.GroupResponseMessagePacket;
import com.pjmike.protocol.response.ResponsePacket;
import com.pjmike.connection.ConnectionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/09
 */
public class GroupRequestMessageHandler extends SimpleChannelInboundHandler<GroupRequestMessagePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupRequestMessagePacket msg) throws Exception {
        String groupId = msg.getGroupId();
        String message = msg.getMessage();
        System.err.println("读取到群聊消息："+ JSON.toJSONString(msg));
        GroupResponseMessagePacket responseMessagePacket = new GroupResponseMessagePacket();
        responseMessagePacket.setGroupId(groupId);
        responseMessagePacket.setMessage(message);
        responseMessagePacket.setFromUser(ConnectionUtil.getConnection(ctx.channel()));

        ChannelGroup channelGroup = ConnectionUtil.getChannelGroup(groupId);
        if (channelGroup == null) {
            ctx.channel().writeAndFlush(new ResponsePacket("群组：[ "+groupId+" ] 不存在"));
            return;
        } else {
            channelGroup.writeAndFlush(responseMessagePacket);
        }
    }
}
