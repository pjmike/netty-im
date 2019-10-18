package com.pjmike.protocol.serializer;

import com.alibaba.fastjson.JSON;

/**
 * @description: JSON序列化
 * @author: pjmike
 * @create: 2019/10/06
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
