package com.pjmike.protocol.codec;

import com.pjmike.protocol.Command;
import com.pjmike.protocol.Packet;
import com.pjmike.protocol.request.*;
import com.pjmike.protocol.response.CreateGroupResponsePacket;
import com.pjmike.protocol.response.GroupResponseMessagePacket;
import com.pjmike.protocol.response.LoginResponsePacket;
import com.pjmike.protocol.response.ResponsePacket;
import com.pjmike.protocol.serializer.JSONSerializer;
import com.pjmike.protocol.serializer.SerializeAlgorithmEnum;
import com.pjmike.protocol.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 编解码器
 * @author: pjmike
 * @create: 2019/10/06
 */
public class PacketCodec extends ByteToMessageCodec<Packet> {
    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private final Map<Byte, Serializer> serializerMap;

    public PacketCodec() {
        this.packetTypeMap = new ConcurrentHashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(Command.CHAT_MESSAGE, MessagePacket.class);
        packetTypeMap.put(Command.NORMAL_RESPONSE, ResponsePacket.class);
        packetTypeMap.put(Command.NORMAL_REQUEST, RequestPacket.class);
        packetTypeMap.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        packetTypeMap.put(Command.CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        packetTypeMap.put(Command.GOURP_REQUEST_MESSAGE, GroupRequestMessagePacket.class);
        packetTypeMap.put(Command.GOURP_RESPONSE_MESSAGE, GroupResponseMessagePacket.class);
        packetTypeMap.put(Command.LOGOUT_REQUEST, LogoutRequestPacket.class);

        this.serializerMap = new ConcurrentHashMap<>();
        serializerMap.put(SerializeAlgorithmEnum.JSON.getType(), new JSONSerializer());
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        //序列化算法
        byte algorithm = packet.getAlgorithm();
        byte[] content = Serializer.DEFAULT.serialize(packet);

        byteBuf.writeInt(Packet.MAGIC_NUMBER);
        byteBuf.writeByte(algorithm);
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(content.length);
        byteBuf.writeBytes(content);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //跳过魔数
        byteBuf.skipBytes(4);
        //序列化算法
        byte algorithm = byteBuf.readByte();

        //指令
        byte command = byteBuf.readByte();

        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];

        byteBuf.readBytes(bytes);
        //根据指令和序列化算法编号查找对应的Packet类和对应的序列化算法
        Class<? extends Packet> packet = getPacket(command);
        Serializer serializer = getSerializer(algorithm);
        if (packet == null || serializer == null) {
            throw new NullPointerException("serializer or packet class is null");
        }
        Packet deserialize = serializer.deserialize(packet, bytes);
        list.add(deserialize);
    }

    private Serializer getSerializer(Byte type) {
        return serializerMap.get(type);
    }

    private Class<? extends Packet> getPacket(Byte command) {
        return packetTypeMap.get(command);
    }
}
