package com.ntdq.power_station.nettyHandler.tcp.modbus.util;

import com.ntdq.power_station.nettyHandler.tcp.modbus.domain.ModBusHeader;
import io.netty.buffer.ByteBuf;

public class ModBusHeaderEncoder {

    /**
     * 对投书局进行编码
     *
     * @param byteBuf
     * @param modBusHeader
     */
    public static void encode(ByteBuf byteBuf, ModBusHeader modBusHeader) {
        byteBuf.writeShort(modBusHeader.getTransactionId());
        byteBuf.writeShort(modBusHeader.getProtocolId());
        byteBuf.writeShort(modBusHeader.getLength());
        byteBuf.writeByte(modBusHeader.getUnitId());
    }
}
