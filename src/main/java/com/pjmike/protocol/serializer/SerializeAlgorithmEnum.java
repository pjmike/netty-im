package com.pjmike.protocol.serializer;

import lombok.Getter;

import java.util.Arrays;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/06
 */
@Getter
public enum  SerializeAlgorithmEnum {
    /**
     * fastjson
     */
    JSON((byte)1);
    private byte type;

    SerializeAlgorithmEnum(byte type) {
        this.type = type;
    }

    public SerializeAlgorithmEnum get(Byte type) {
        if (type == null) {
            return null;
        }
        return Arrays.stream(values()).filter(t -> t.getType() == type)
                .findFirst().orElse(null);
    }
}
