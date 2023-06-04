package com.ntdq.power_station.nettyHandler.udp.power;

import com.ntdq.power_station.domain.powerStation.ByteMessage;
import com.ntdq.power_station.domain.powerStation.PowerStationReport;
import com.ntdq.power_station.nettyHandler.Decoder;
import com.ntdq.power_station.util.CommonByteUtils;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PowerStationReportDecoder extends Decoder {

    private static final Logger logger = LoggerFactory.getLogger(PowerStationReportDecoder.class);

    /**
     * 读取最小字节数量
     */
    public static final int MIN_SIZE = 15;

    @Override
    protected ByteMessage decodeByteArrToMessage(ByteBuf in) {
        logger.info("解码器开始解析字节数据...");
        if (in.readableBytes() < MIN_SIZE){
            logger.info("读取到无用的字节数据:{}",in.readableBytes());
            return null;
        }
        in.markReaderIndex();
        byte[] byteData = new byte[in.readableBytes()];
        in.readBytes(byteData);
        String data = CommonByteUtils.BinaryToHexString(byteData);
        logger.info("解析出的数据为:{}",data);
        return PowerStationReport.build(byteData);
    }
}
