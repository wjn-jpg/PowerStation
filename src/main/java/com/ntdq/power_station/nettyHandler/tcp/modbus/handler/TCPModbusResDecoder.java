package com.ntdq.power_station.nettyHandler.tcp.modbus.handler;

import com.ntdq.power_station.nettyHandler.tcp.modbus.domain.TCPModbusByteBufHolder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * modbus数据解码
 */
public class TCPModbusResDecoder extends ByteToMessageDecoder {

    /**
     * 日志
     */
    private final static Logger logger = LoggerFactory.getLogger(TCPModbusResDecoder.class);


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        in.resetReaderIndex();
        logger.info("ModBus数据解码数量:{}",in.readableBytes());
        ByteBuf byteBuf = Unpooled.copiedBuffer(in);
        TCPModbusByteBufHolder tcpModbusByteBufHolder = new TCPModbusByteBufHolder(byteBuf);
        out.add(tcpModbusByteBufHolder);
        //将读写指针重置为0
        in.clear();
    }
}
