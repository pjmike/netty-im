package com.pjmike.connection;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 保存用户ID 与 Channel的映射关系
 * @author: pjmike
 * @create: 2019/10/07
 */
@Slf4j
public class ConnectionUtil {
    private static final Map<String, Channel> USER_ID_CHANNEL_MAP = new ConcurrentHashMap<>();
    private static final Map<String, ChannelGroup> GROUP_ID_CHANNEL_GROUP_MAP = new ConcurrentHashMap<>();
    public static void bindConnection(Connection connection, Channel channel) {
        USER_ID_CHANNEL_MAP.put(connection.getUserId(), channel);
        //设置AttributeMap
        channel.attr(Attributes.SESSION_ATTRIBUTE_KEY).set(connection);
    }

    public static void bindGroupConnection(String groupId, ChannelGroup channels) {
        GROUP_ID_CHANNEL_GROUP_MAP.put(groupId, channels);
    }
    public static void unbindConnection(Channel channel) {
        if (hasLogin(channel)) {
            Connection connection = getConnection(channel);
            //从Map除去映射关系
            USER_ID_CHANNEL_MAP.remove(connection.getUserId());
            //设置为NULL
            channel.attr(Attributes.SESSION_ATTRIBUTE_KEY).set(null);
            log.info("{} 退出登录!", connection);
        }
    }

    public static ChannelGroup getChannelGroup(String groupId) {
        return GROUP_ID_CHANNEL_GROUP_MAP.get(groupId);
    }
    /**
     * 判断是否之前已经登录过
     *
     * @param channel
     * @return
     */
    public static boolean hasLogin(Channel channel) {
        return getConnection(channel) != null;
    }

    public static Connection getConnection(Channel channel) {
        return channel.attr(Attributes.SESSION_ATTRIBUTE_KEY).get();
    }

    public static Channel getChannel(String userId) {
        return USER_ID_CHANNEL_MAP.get(userId);
    }

    /**
     * 获取所有在线用户
     *
     * @return
     */
    public static Set<String> getAllConnection() {
        return USER_ID_CHANNEL_MAP.keySet();
    }
}
