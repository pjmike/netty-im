package com.pjmike.protocol;

/**
 * @author: pjmike
 * @create: 2019/10/06
 */
public abstract class Packet {
    /**
     * 魔数
     */
    public static final int MAGIC_NUMBER = 0x12345678;

    /**
     * 获取指令编号
     *
     * @return
     */
    public abstract byte getCommand();

    /**
     * 获取序列化算法编号
     *
     * @return
     */
    public abstract byte getAlgorithm();
}
