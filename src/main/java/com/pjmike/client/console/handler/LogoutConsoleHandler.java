package com.pjmike.client.console.handler;

import com.pjmike.client.console.ConsoleHandler;
import com.pjmike.protocol.request.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/09
 */
public class LogoutConsoleHandler implements ConsoleHandler {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket requestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(requestPacket);
    }
}
