package com.pjmike.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/07
 */
public interface ConsoleHandler {
    void exec(Scanner scanner, Channel channel);
}
