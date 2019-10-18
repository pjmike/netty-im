package com.pjmike.server.handler;

import com.pjmike.protocol.request.MessagePacket;
import com.pjmike.protocol.response.ResponsePacket;
import com.pjmike.connection.ConnectionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/07
 */
@Slf4j
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessagePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessagePacket packet) {
        Channel toChannel = ConnectionUtil.getChannel(packet.getToUserId());
        if (Objects.isNull(toChannel)) {
            log.info("发送失败，目标用户 {} 没有登录",packet.getToUserId());
            ResponsePacket responsePacket = new ResponsePacket();
            responsePacket.setMessage("用户：[“"+packet.getToUserId()+"] 没有登录");
            ctx.channel().writeAndFlush(responsePacket);
            return;
        }
        //设置发送者ID
        packet.setFromUserId(ConnectionUtil.getConnection(ctx.channel()).getUserId());
        //服务端向目标ID进行转发消息
        toChannel.writeAndFlush(packet);
    }
}
