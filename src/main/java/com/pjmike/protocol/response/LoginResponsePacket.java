package com.pjmike.protocol.response;

import com.pjmike.protocol.AbstractPacket;
import com.pjmike.protocol.Command;
import com.pjmike.protocol.Packet;
import lombok.Data;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/06
 */
@Data
public class LoginResponsePacket extends ResponsePacket {
    private String userId;
    private String username;
    private boolean success;

    @Override
    public byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
