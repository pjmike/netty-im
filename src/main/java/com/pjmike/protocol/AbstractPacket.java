package com.pjmike.protocol;

import com.pjmike.protocol.serializer.SerializeAlgorithmEnum;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/06
 */
public abstract class AbstractPacket extends Packet{
    @Override
    public byte getAlgorithm() {
        return SerializeAlgorithmEnum.JSON.getType();
    }
}
