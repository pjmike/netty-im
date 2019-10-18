package com.pjmike.server.handler;

import com.pjmike.protocol.request.CreateGroupRequestPacket;
import com.pjmike.protocol.response.CreateGroupResponsePacket;
import com.pjmike.connection.ConnectionUtil;
import com.pjmike.util.UUIDUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/09
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        ChannelGroup channels = new DefaultChannelGroup(ctx.executor());
        List<String> userNameList = new ArrayList<>();
        msg.getUserIds().forEach(userId -> {
            Channel channel = ConnectionUtil.getChannel(userId);
            if (channel != null) {
                channels.add(channel);
                userNameList.add(ConnectionUtil.getConnection(channel).getUsername());
            }
        });
        //创建群聊ID
        String groupId = UUIDUtil.getID();

        //返回创建群聊的响应
        CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(groupId);
        responsePacket.setGroupName(msg.getGroupName());
        responsePacket.setUserNameList(userNameList);

        //发送创建群聊消息
        channels.writeAndFlush(responsePacket);

        System.out.printf("群组创建成功，群ID为：%s，群成员ID为：%s\n", groupId, msg.getUserIds());

        //保存群组信息
        ConnectionUtil.bindGroupConnection(groupId, channels);
    }
}
