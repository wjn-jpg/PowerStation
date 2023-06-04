package com.ntdq.power_station.nettyHandler.tcp.master104.handler;

import com.ntdq.power_station.nettyHandler.DefaultChannelHandler;
import com.ntdq.power_station.nettyHandler.tcp.master104.core.ControlSendAndRecCountPool;
import com.ntdq.power_station.nettyHandler.tcp.master104.core.Iec104ThreadLocal;
import com.ntdq.power_station.util.ByteUtil;
import com.ntdq.power_station.util.Iec104Util;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理S帧 每次拿到S要解析序列号 并且释放线程任务锁
 */
public class SysSFrameClientHandler extends DefaultChannelHandler<ByteBuf> {

    private static final Logger logger = LoggerFactory.getLogger(SysSFrameClientHandler.class);

    @Override
    protected void read(ChannelHandlerContext context, ByteBuf msg) {
        byte[] data = new byte[msg.readableBytes()];
        msg.readBytes(data);
        short accept = Iec104Util.getSend(ByteUtil.getByte(data, 2, 4));
        logger.info("拿到的S帧数据序列号:{}",accept);
        ControlSendAndRecCountPool controlSendAndRecCountPool = Iec104ThreadLocal.getControlSendAndRecCountPool();
        controlSendAndRecCountPool.setAccept(accept);
//        Iec104ThreadLocal.getControlSendAndRecCountPool().setAccept(accept);
        msg.writeBytes(data);
        context.fireChannelRead(msg);
    }
}
