package com.pjmike.protocol.request;

import com.pjmike.protocol.AbstractPacket;
import com.pjmike.protocol.Command;
import lombok.Data;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/09
 */
@Data
public class GroupRequestMessagePacket extends AbstractPacket {
    private String groupId;
    private String message;
    @Override
    public byte getCommand() {
        return Command.GOURP_REQUEST_MESSAGE;
    }
}
