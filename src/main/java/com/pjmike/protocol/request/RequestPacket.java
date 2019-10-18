package com.pjmike.protocol.request;

import com.pjmike.protocol.AbstractPacket;
import com.pjmike.protocol.Command;
import lombok.Data;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/08
 */
@Data
public class RequestPacket extends AbstractPacket {
    private String message;
    @Override
    public byte getCommand() {
        return Command.NORMAL_REQUEST;
    }
}
