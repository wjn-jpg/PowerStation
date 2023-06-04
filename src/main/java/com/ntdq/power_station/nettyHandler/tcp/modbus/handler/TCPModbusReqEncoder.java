package com.ntdq.power_station.nettyHandler.tcp.modbus.handler;

import cn.hutool.core.util.ByteUtil;
import com.ntdq.power_station.nettyHandler.tcp.modbus.domain.ModBusMessage;
import com.ntdq.power_station.nettyHandler.tcp.modbus.util.ModBusHeaderEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TCPModbusReqEncoder extends MessageToByteEncoder<ModBusMessage> {

    private final static Logger logger = LoggerFactory.getLogger(TCPModbusReqEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, ModBusMessage modBusMessage, ByteBuf byteBuf) throws Exception {
        logger.info("-----------TCPModbusReqEncoder encode begin------------");
        //header
        ModBusHeaderEncoder.encode(byteBuf, modBusMessage.getModBusHeader());
        logger.info("header:" + modBusMessage.getModBusHeader().toString());

        //functionCode
        int functionCode = modBusMessage.getModBusPayload().getFunctionCode();
        logger.info("functionCode:" + functionCode);

        byteBuf.writeByte(modBusMessage.getModBusPayload().getFunctionCode());
        byteBuf.writeBytes(modBusMessage.getModBusPayload().getData());
//        byte[] bytes = new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(bytes);
//        logger.info("输出:{}", bytes);
        logger.info("TCPModbus输出编码完成...");

    }

    public static void main(String[] args) {

        byte[] bytes = ByteUtil.shortToBytes((short) 584);
        System.out.println(bytes);
    }
}
