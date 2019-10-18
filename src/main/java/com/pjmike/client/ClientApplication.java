package com.pjmike.client;

import com.pjmike.client.console.*;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/07
 */
@Slf4j
public class ClientApplication{
    private static Channel channel = null;
    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();
        //开启Netty服务
        nettyClient.start();
        channel = nettyClient.channel;
        startCommand();
    }

    public static void startCommand() {
        CommandThread thread = new CommandThread();
        //开启控制台线程
        log.info("启动客户端成功,开启控制台线程...");
        new Thread(thread).start();
    }



    static class CommandThread implements Runnable {
        @Override
        public void run() {
            ConsoleHandlerChooser.showUsage();
            while (!Thread.interrupted()) {
                ConsoleHandlerChooser.handle(channel);
            }
        }
    }


}
