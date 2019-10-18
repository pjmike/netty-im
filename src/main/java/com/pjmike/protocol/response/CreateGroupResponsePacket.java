package com.pjmike.protocol.response;

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
public class CreateGroupResponsePacket extends AbstractPacket {
    private boolean success;
    private String groupId;
    private String groupName;
    private List<String> userNameList;
    @Override
    public byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
