package com.pjmike.client.handler;

import com.pjmike.protocol.response.LoginResponsePacket;
import com.pjmike.protocol.response.ResponsePacket;
import com.pjmike.connection.ConnectionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/07
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<ResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ResponsePacket responsePacket) {
        if (responsePacket instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) responsePacket;
            String username = loginResponsePacket.getUsername();
            String userId = loginResponsePacket.getUserId();
            if (loginResponsePacket.isSuccess()) {
                System.out.printf("用户名：%s，id: %s 登录成功\n", username, userId);
            } else {
                System.out.printf("用户名：%s，登录失败原因：%s\n",username,loginResponsePacket.getMessage());
            }
        } else {
            System.out.printf("服务端响应消息：%s \n", responsePacket.getMessage());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭！");
        ConnectionUtil.unbindConnection(ctx.channel());
    }
}
