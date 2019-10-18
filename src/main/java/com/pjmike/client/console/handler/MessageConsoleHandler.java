package com.pjmike.client.console.handler;


import com.pjmike.client.console.ConsoleHandler;
import com.pjmike.protocol.request.MessagePacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/07
 */
public class MessageConsoleHandler implements ConsoleHandler {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入目标用户的ID以及消息内容，例如: pjmike,你真棒!");
        String[] content = scanner.nextLine().split(",");
        MessagePacket requestPacket = new MessagePacket();
        requestPacket.setToUserId(content[0]);
        requestPacket.setMessage(content[1]);
        channel.writeAndFlush(requestPacket);
    }
}
