package com.pjmike.protocol.request;

import com.pjmike.protocol.AbstractPacket;
import com.pjmike.protocol.Command;
import lombok.Data;

/**
 * @description: 登录请求信息
 * @author: pjmike
 * @create: 2019/10/06
 */
@Data
public class LoginRequestPacket extends AbstractPacket {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    @Override
    public byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
