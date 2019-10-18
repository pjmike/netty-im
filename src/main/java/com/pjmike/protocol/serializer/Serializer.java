package com.pjmike.protocol.serializer;

import java.io.IOException;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/06
 */
public interface Serializer {
    Serializer DEFAULT = new JSONSerializer();
    /**
     * 将Java对象转换成字节流
     *
     * @param object
     * @return
     */
    byte[] serialize(Object object) throws IOException;

    /**
     * 将字节数组反序列化为Java对象
     *
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes) throws IOException;
}
