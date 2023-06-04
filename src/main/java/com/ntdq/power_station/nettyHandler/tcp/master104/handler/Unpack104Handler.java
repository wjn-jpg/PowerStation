package com.ntdq.power_station.nettyHandler.tcp.master104.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Unpack104Handler extends ByteToMessageDecoder {

    private static final byte HEAD_DATA = 0x68;

    private static final Logger logger = LoggerFactory.getLogger(Unpack104Handler.class);

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) throws Exception {
        //记录包头开始的index
        int beginReader = 0;
        int newDataLength = 0;
        while (true){
            beginReader = byteBuf.readerIndex();
            logger.info("包开头的index:{}",beginReader);
            //记录一个标志用于重置
            byteBuf.markReaderIndex();
            //如果读到了协议的开始标志 结束while循环
            if (byteBuf.readByte() == HEAD_DATA){
                //读取包长度
                byte newDataLengthByte = byteBuf.readByte();
                newDataLength = newDataLengthByte & 0xFF;
                break;
            }
            continue;
        }
        if (byteBuf.readableBytes() < newDataLength){
            byteBuf.readerIndex(beginReader);
            return;
        }
        //因为读取了一个字节标志头 然后读取了一个字节长度 所以要将索引+2
        newDataLength = newDataLength + 2;
        //恢复指针
        byteBuf.readerIndex(beginReader);
//        logger.info("首先读取:{}长度",newDataLength);
        ByteBuf data = byteBuf.readBytes(newDataLength);
        out.add(data);
    }

}
