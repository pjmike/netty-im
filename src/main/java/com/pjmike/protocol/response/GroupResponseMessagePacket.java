package com.pjmike.protocol.response;

import com.pjmike.protocol.AbstractPacket;
import com.pjmike.protocol.Command;
import com.pjmike.connection.Connection;
import lombok.Data;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/09
 */
@Data
public class GroupResponseMessagePacket extends AbstractPacket {
    private Connection fromUser;
    private String groupId;
    private String message;
    @Override
    public byte getCommand() {
        return Command.GOURP_RESPONSE_MESSAGE;
    }
}
