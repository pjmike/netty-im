package com.pjmike.client.console.handler;

import com.pjmike.client.console.ConsoleHandler;
import com.pjmike.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/07
 */
public class LoginConsoleHandler implements ConsoleHandler {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入用户名和密码，例如：pjmike 12345678");
        String line = scanner.nextLine();
        String[] s = line.split(" ");
        LoginRequestPacket requestPacket = new LoginRequestPacket();
        requestPacket.setUsername(s[0]);
        requestPacket.setPassword(s[1]);
        channel.writeAndFlush(requestPacket);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
