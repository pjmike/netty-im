package com.pjmike.util;

import java.util.UUID;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/07
 */
public class UUIDUtil {
    public static String getID() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
