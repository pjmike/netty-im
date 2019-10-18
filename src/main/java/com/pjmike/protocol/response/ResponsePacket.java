package com.pjmike.protocol.response;

import com.pjmike.protocol.AbstractPacket;
import com.pjmike.protocol.Command;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/07
 */
@Data
@NoArgsConstructor
public class ResponsePacket extends AbstractPacket {
    private String message;

    public ResponsePacket(String message) {
        this.message = message;
    }

    @Override
    public byte getCommand() {
        return Command.NORMAL_RESPONSE;
    }
}
