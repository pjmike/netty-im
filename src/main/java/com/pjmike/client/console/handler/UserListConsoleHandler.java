package com.pjmike.client.console.handler;

import com.pjmike.client.console.ConsoleHandler;
import com.pjmike.protocol.request.RequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/08
 */
public class UserListConsoleHandler implements ConsoleHandler {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("当前在线的用户有：");
        RequestPacket requestPacket = new RequestPacket();
        requestPacket.setMessage("list");
        channel.writeAndFlush(requestPacket);
    }
}
