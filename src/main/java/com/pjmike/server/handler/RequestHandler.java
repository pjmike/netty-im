package com.pjmike.server.handler;

import com.alibaba.fastjson.JSON;
import com.pjmike.protocol.request.RequestPacket;
import com.pjmike.protocol.response.ResponsePacket;
import com.pjmike.connection.ConnectionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/08
 */
@Slf4j
public class RequestHandler extends SimpleChannelInboundHandler<RequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RequestPacket requestPacket) throws Exception {
        String message = requestPacket.getMessage();
        if (StringUtils.isEmpty(message) || !message.equals("list")) {
            return;
        }
        Set<String> allUsers = ConnectionUtil.getAllConnection();
        ResponsePacket responsePacket = new ResponsePacket();
        responsePacket.setMessage(JSON.toJSONString(allUsers));
        ctx.channel().writeAndFlush(responsePacket);
    }
}
