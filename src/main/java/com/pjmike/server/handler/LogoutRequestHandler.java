package com.pjmike.server.handler;

import com.pjmike.protocol.request.LogoutRequestPacket;
import com.pjmike.connection.ConnectionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/09
 */
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception {
        ConnectionUtil.unbindConnection(ctx.channel());
    }
}
