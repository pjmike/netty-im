package com.pjmike.util;

import com.pjmike.protocol.Packet;
import com.pjmike.protocol.request.LoginRequestPacket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: "内存数据库"
 * @author: pjmike
 * @create: 2019/10/07
 */
public class MemoryUtil {
    private static Map<String, String> store = new ConcurrentHashMap<>();

    public static boolean valid(Packet packet) {
        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket user = (LoginRequestPacket) packet;
            if (!store.containsKey(user.getUsername())) {
                store.putIfAbsent(user.getUsername(), user.getPassword());
                return true;
            } else {
                return store.get(user.getUsername()).equals(user.getPassword());
            }
        }
        return false;
    }
}
