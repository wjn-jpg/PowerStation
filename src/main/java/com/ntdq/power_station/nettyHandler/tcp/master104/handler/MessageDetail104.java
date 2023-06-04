package com.ntdq.power_station.nettyHandler.tcp.master104.handler;

import com.ntdq.power_station.nettyHandler.tcp.master104.core.Iec104ThreadLocal;
import com.ntdq.power_station.util.ByteUtil;
import com.ntdq.power_station.util.Iec104Util;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MessageDetail104 extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        byte[] data = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(data);
        short accept = Iec104Util.getSend(ByteUtil.getByte(data, 2, 4));
        Iec104ThreadLocal.getControlSendAndRecCountPool().setAccept(accept);
        byteBuf.writeBytes(data);
        channelHandlerContext.fireChannelRead(byteBuf);
    }
}
