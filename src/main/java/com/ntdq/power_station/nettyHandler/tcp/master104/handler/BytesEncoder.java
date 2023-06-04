package com.ntdq.power_station.nettyHandler.tcp.master104.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BytesEncoder extends MessageToByteEncoder<byte[]> {

    private final static Logger logger = LoggerFactory.getLogger(BytesEncoder.class);

    /**
     * 此处拿到的ThreadLocal的pool 与在ActivePool的线程是一样的所以拿到的ThreadLocal里面的value是一样的
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link MessageToByteEncoder} belongs to
     * @param msg the message to encode
     * @param out the {@link ByteBuf} into which the encoded message will be written
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, byte[] msg, ByteBuf out) throws Exception {
        logger.info("发送的send报文:{}", msg);
        out.writeBytes(msg);
    }
}
