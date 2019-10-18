package com.pjmike.server.handler;

import com.pjmike.protocol.response.ResponsePacket;
import com.pjmike.connection.ConnectionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 登录鉴权
 *
 * @author: pjmike
 * @create: 2019/10/08
 */
public class AuthHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
        Channel channel = ctx.channel();
        if (!ConnectionUtil.hasLogin(channel)) {
            System.err.println("开始调用。。");
            ResponsePacket responsePacket = new ResponsePacket();
            responsePacket.setMessage("用户还没有登录,请先进行登录");
            channel.writeAndFlush(responsePacket);
            return;
        } else {
            channel.pipeline().remove(this);
            //触发ChannelPipeline的下一个Handler进行读取操作
            //不能调用ctx.pipeline().fireChannelRead()方法，因为会沿着ChannelPipeline从头开始传播
            ctx.fireChannelRead(o);
        }
    }
}
