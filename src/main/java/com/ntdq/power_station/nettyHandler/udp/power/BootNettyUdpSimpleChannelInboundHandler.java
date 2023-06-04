package com.ntdq.power_station.nettyHandler.udp.power;

import com.ntdq.power_station.domain.powerStation.PowerStationReport;
import com.ntdq.power_station.nettyHandler.DefaultChannelHandler;
import com.ntdq.power_station.redis.queue.ReportQueue;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BootNettyUdpSimpleChannelInboundHandler extends DefaultChannelHandler<PowerStationReport> {

    private static final Logger logger = LoggerFactory.getLogger(BootNettyUdpSimpleChannelInboundHandler.class);

    @Override
    protected void read(ChannelHandlerContext context, PowerStationReport msg) {
        logger.info("此时的数据时间:{}", msg.getMessageRecordTime().toString());
        //放到队列中 异步执行耗时IO操作
        ReportQueue.addDeviceReportData(context.channel().id().asLongText(), msg);
    }


}
