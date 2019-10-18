package com.pjmike.client.console.handler;

import com.alibaba.fastjson.JSON;
import com.pjmike.client.console.ConsoleHandler;
import com.pjmike.protocol.request.GroupRequestMessagePacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/09
 */
public class SendGroupMessageConsoleHandler implements ConsoleHandler {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入你要发送的群组ID和群聊消息，比如 sdff2342 大家好呀，我是pjmike");
        String[] content = scanner.nextLine().split(" ");
        GroupRequestMessagePacket packet = new GroupRequestMessagePacket();
        packet.setGroupId(content[0]);
        packet.setMessage(content[1]);
        channel.writeAndFlush(packet);
    }
}
