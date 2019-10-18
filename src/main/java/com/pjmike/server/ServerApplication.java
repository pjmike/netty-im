package com.pjmike.server;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/07
 */
public class ServerApplication {
    public static void main(String[] args) {
        NettyServer nettyServer = new NettyServer(8888);
        nettyServer.start();
    }
}
