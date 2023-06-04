package com.ntdq.power_station.nettyHandler;

import com.ntdq.power_station.domain.powerStation.ByteMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 解码 接收ByteBuf对象
 * 具体解码方法交给子类实现
 */
public abstract class Decoder extends MessageToMessageDecoder<ByteBuf> {

    private static final Logger logger = LoggerFactory.getLogger(Decoder.class);

    protected abstract ByteMessage decodeByteArrToMessage(ByteBuf msg);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        ByteMessage byteMessage = this.decodeByteArrToMessage(msg);
        if (byteMessage!=null){
            out.add(byteMessage);
        }
    }
}
