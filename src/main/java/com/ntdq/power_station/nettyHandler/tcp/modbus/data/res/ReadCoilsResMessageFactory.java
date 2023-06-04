package com.ntdq.power_station.nettyHandler.tcp.modbus.data.res;

import com.ntdq.power_station.nettyHandler.tcp.modbus.data.RecAndWriMessage;
import com.ntdq.power_station.nettyHandler.tcp.modbus.domain.ModBusHeader;
import com.ntdq.power_station.nettyHandler.tcp.modbus.domain.ModBusPayload;
import com.ntdq.power_station.util.ByteUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadCoilsResMessageFactory implements RecAndWriMessage {

    private static final Logger logger = LoggerFactory.getLogger(ReadCoilsResMessageFactory.class);

    @Override
    public void acceptMessage(Channel channel, ModBusHeader modBusHeader, ModBusPayload modBusPayload) {
        logger.info("-----------recMessage:读线圈 begin------------");
        logger.info("header: " + modBusHeader.toString());
        logger.info("functionCode: " + modBusPayload.getFunctionCode());
        logger.info("data length: " + modBusPayload.getDataLength());
        logger.info("data hex data: " + ByteUtil.bytesToHexString(modBusPayload.getData()));
        logger.info("-----------recMessage:读线圈 end------------");
    }
}
