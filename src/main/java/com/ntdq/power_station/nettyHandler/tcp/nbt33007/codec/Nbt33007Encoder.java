package com.ntdq.power_station.nettyHandler.tcp.nbt33007.codec;

import com.ntdq.power_station.nettyHandler.tcp.nbt33007.message.MessageDetail;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.util.Nbt33007CodecUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Description : NBT33007编码器
 * @Author : Kang
 * @Date : 2023/4/19 16:27
 * @Version : 1.0
 */
public class Nbt33007Encoder extends MessageToByteEncoder<MessageDetail> {

    @Override
    protected void encode(ChannelHandlerContext ctx, MessageDetail msg, ByteBuf out) throws Exception {
        byte[] bytes = Nbt33007CodecUtil.encode(msg);
        out.writeBytes(bytes);
    }
}
