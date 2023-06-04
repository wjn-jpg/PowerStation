package com.ntdq.power_station.nettyHandler.tcp.modbus.domain;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.DefaultByteBufHolder;

/**
 * 处理ByteBuf数据实体类
 */
public class TCPModbusByteBufHolder extends DefaultByteBufHolder {

    public TCPModbusByteBufHolder(ByteBuf byteBuf){
        super(byteBuf);
    }



}
