package com.pjmike.client.console.handler;

import com.pjmike.client.console.ConsoleHandler;
import com.pjmike.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/09
 */
public class GroupCreateConsoleHandler implements ConsoleHandler {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("创建群聊，请输入成员ID列表，比如: ad23sdfd,osdf342,234sdfs");
        String[] userIds = scanner.nextLine().split(",");
        System.out.println("输入群聊名称：");
        String groupName = scanner.nextLine();
        CreateGroupRequestPacket packet = new CreateGroupRequestPacket();
        packet.setUserIds(Arrays.asList(userIds));
        packet.setGroupName(groupName);

        channel.writeAndFlush(packet);
    }
}
