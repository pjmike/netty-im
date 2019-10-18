package com.pjmike.server.handler;

import com.pjmike.protocol.request.LoginRequestPacket;
import com.pjmike.protocol.response.LoginResponsePacket;
import com.pjmike.connection.Connection;
import com.pjmike.connection.ConnectionUtil;
import com.pjmike.util.MemoryUtil;
import com.pjmike.util.UUIDUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/07
 */
@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket responsePacket = new LoginResponsePacket();
        String username = loginRequestPacket.getUsername();
        responsePacket.setUsername(username);
        //进行身份验证
        if (MemoryUtil.valid(loginRequestPacket)) {
            responsePacket.setSuccess(true);
            responsePacket.setUserId(UUIDUtil.getID());
            log.info("用户：{} 登录成功",loginRequestPacket.getUsername());
            //保存用户ID与Channel的映射关系
            ConnectionUtil.bindConnection(new Connection(responsePacket.getUserId(),username),ctx.channel());
        } else {
            log.warn("用户登录失败，校验错误");
            responsePacket.setSuccess(false);
            responsePacket.setMessage("密码错误，校验失败");
        }
        ctx.writeAndFlush(responsePacket);
    }
}
