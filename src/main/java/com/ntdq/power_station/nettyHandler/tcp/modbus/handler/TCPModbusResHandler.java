package com.ntdq.power_station.nettyHandler.tcp.modbus.handler;

import com.ntdq.power_station.nettyHandler.tcp.modbus.data.DataRecAndWriFactory;
import com.ntdq.power_station.nettyHandler.tcp.modbus.data.RecAndWriMessage;
import com.ntdq.power_station.nettyHandler.tcp.modbus.domain.ModBusHeader;
import com.ntdq.power_station.nettyHandler.tcp.modbus.domain.ModBusPayload;
import com.ntdq.power_station.nettyHandler.tcp.modbus.domain.TCPModbusByteBufHolder;
import com.ntdq.power_station.nettyHandler.tcp.modbus.util.ModBusChannelManager;
import com.ntdq.power_station.nettyHandler.tcp.modbus.util.ModBusHeaderDecoder;
import com.ntdq.power_station.socketStrategy.Decorator.DecoratorDemo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TCPModbusResHandler extends SimpleChannelInboundHandler<TCPModbusByteBufHolder> {

    private static final Logger logger = LoggerFactory.getLogger(TCPModbusResDecoder.class);

    public static final int HEADER_LENGTH = 8;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("ModBus客户端上线");
        Channel channel = ctx.channel();
        String ip = channel.remoteAddress().toString().split(":")[0];
        if (ip.startsWith("/")){
            ip = ip.substring(1);
        }
        ModBusChannelManager.addChannel(ip, channel);
        logger.info("添加线圈channel:{}", channel);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("ModBus客户端下线");
        Channel channel = ctx.channel();
        ModBusChannelManager.remove(channel.remoteAddress().toString());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TCPModbusByteBufHolder msg) throws Exception {
        logger.info("拿到TCPModbusByteBufHolder对象...");
        int dataLength = 0;
        ByteBuf content = msg.content();
        int totalLength = content.readableBytes();
        if (totalLength < HEADER_LENGTH) {
            logger.info("modbusTCP数据丢失...");
            return;
        }
        ModBusHeader modBusHeader = ModBusHeaderDecoder.decode(content);
        logger.info("modBusHeader:{}", modBusHeader);
        ModBusPayload modBusPayload = new ModBusPayload();
        //读取指令类型
        short functionCode = content.readUnsignedByte();
        logger.info("收到指令类型Code:{}",functionCode);
        modBusPayload.setFunctionCode(functionCode);
        if (totalLength > HEADER_LENGTH) {
            dataLength = totalLength - HEADER_LENGTH;
        }
        modBusPayload.setDataLength((short) dataLength);
        byte[] data = new byte[dataLength];
        content.readBytes(data);
        modBusPayload.setData(data);
        RecAndWriMessage messageFactory = DataRecAndWriFactory.getMessageFactory(functionCode);
        if (messageFactory != null) {
            messageFactory.acceptMessage(ctx.channel(), modBusHeader, modBusPayload);
        } else {
            logger.info("没有此类型的功能码!");
        }
    }
}
