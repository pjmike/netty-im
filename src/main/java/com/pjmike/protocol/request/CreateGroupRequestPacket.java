package com.pjmike.protocol.request;

import com.pjmike.protocol.AbstractPacket;
import com.pjmike.protocol.Command;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/09
 */
@Data
public class CreateGroupRequestPacket extends AbstractPacket {
    private List<String> userIds;
    private String groupName;
    @Override
    public byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
