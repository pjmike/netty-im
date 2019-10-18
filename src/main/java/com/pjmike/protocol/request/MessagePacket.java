package com.pjmike.protocol.request;

import com.pjmike.protocol.AbstractPacket;
import com.pjmike.protocol.Command;
import lombok.Data;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/07
 */
@Data
public class MessagePacket extends AbstractPacket {
    private String fromUserId;
    private String toUserId;
    private String message;
    @Override
    public byte getCommand() {
        return Command.CHAT_MESSAGE;
    }
}
