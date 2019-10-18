package com.pjmike.connection;

import io.netty.util.AttributeKey;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/07
 */
public interface Attributes {
    AttributeKey<Connection> SESSION_ATTRIBUTE_KEY = AttributeKey.newInstance("connection");
}
