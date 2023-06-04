package com.ntdq.power_station.nettyHandler.tcp.modbus.data;

import com.ntdq.power_station.nettyHandler.tcp.modbus.domain.ModBusHeader;
import com.ntdq.power_station.nettyHandler.tcp.modbus.domain.ModBusPayload;
import io.netty.channel.Channel;


public interface RecAndWriMessage {


    void acceptMessage(Channel channel, ModBusHeader modBusHeader, ModBusPayload modBusPayload);


}
