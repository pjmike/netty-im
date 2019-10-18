package com.pjmike.client.console;

import com.pjmike.client.console.handler.*;
import io.netty.channel.Channel;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/09
 */
public class ConsoleHandlerChooser {
    private static Map<String, ConsoleHandler> map = new ConcurrentHashMap<>();
    static {
        map.put("login", new LoginConsoleHandler());
        map.put("logout", new LogoutConsoleHandler());
        map.put("chat", new MessageConsoleHandler());
        map.put("list", new UserListConsoleHandler());
        map.put("createGroup", new GroupCreateConsoleHandler());
        map.put("groupChat", new SendGroupMessageConsoleHandler());
    }

    public static void handle(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        String msg = scanner.nextLine();
        if (StringUtils.isEmpty(msg)) {
            System.out.println("发送消息不能为空!");
            return;
        }
        if (!map.containsKey(msg)) {
            showUsage();
            return;
        }
        ConsoleHandler consoleHandler = map.get(msg);
        consoleHandler.exec(scanner, channel);
    }
    public static void showUsage() {
        System.out.println("Usage: <cmd> [options]");
        System.out.println("\t<login>\t[启动登录模式]");
        System.out.println("\t<chat>\t[启动单聊模式]");
        System.out.println("\t<createGroup>\t[创建群聊]");
        System.out.println("\t<groupChat>\t[发送群消息]");
    }
}
