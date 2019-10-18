package com.pjmike.protocol;

public interface Command {
    /**
     * 登录指令
     */
    Byte LOGIN_REQUEST = 1;
    /**
     * 登录响应
     */
    Byte LOGIN_RESPONSE = 2;
    /**
     * 发送信息指令
     */
    Byte CHAT_MESSAGE = 3;
    /**
     * 服务端通用响应
     */
    Byte NORMAL_RESPONSE = 4;
    /**
     * 客户端通用请求
     */
    Byte NORMAL_REQUEST = 5;

    /**
     * 创建群组请求
     */
    Byte CREATE_GROUP_REQUEST = 6;

    /**
     * 创建群组响应
     */
    Byte CREATE_GROUP_RESPONSE = 7;

    /**
     * 群聊请求消息
     */
    Byte GOURP_REQUEST_MESSAGE = 8;

    /**
     * 群聊响应消息
     */
    Byte GOURP_RESPONSE_MESSAGE = 9;

    Byte LOGOUT_REQUEST = 10;

    Byte LOGOUT_RESPONSE = 11;
}
