package com.ntdq.power_station.nettyHandler.tcp.nbt33007.codec;

import com.ntdq.power_station.nettyHandler.tcp.nbt33007.message.MessageDetail;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.util.Nbt33007CodecUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Description : NBT33007解码器
 * @Author : Kang
 * @Date : 2023/4/19 16:27
 * @Version : 1.0
 */

public class Nbt33007Decoder extends ByteToMessageDecoder {

    private static final Logger log = LoggerFactory.getLogger(Nbt33007Decoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        byte[] data = new byte[in.readableBytes()];
        in.readBytes(data);
        //String hexData = ByteBufUtil.hexDump(data);
        //log.info("hexData:{}",hexData);
        //log.info("data:{}",data);

        MessageDetail messageDetail = Nbt33007CodecUtil.decode(data);
        out.add(messageDetail);

        in.clear();
    }
}
