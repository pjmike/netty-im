package com.pjmike.protocol.request;

import com.pjmike.protocol.AbstractPacket;
import com.pjmike.protocol.Command;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/09
 */
public class LogoutRequestPacket extends AbstractPacket {
    @Override
    public byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
