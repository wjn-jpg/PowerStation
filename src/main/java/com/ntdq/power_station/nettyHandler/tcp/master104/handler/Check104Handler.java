package com.ntdq.power_station.nettyHandler.tcp.master104.handler;

import com.ntdq.power_station.nettyHandler.tcp.master104.constant.Iec104Constant;
import com.ntdq.power_station.util.CommonByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 检查104报文
 * @author wang_ji_nian
 */
public class Check104Handler extends ChannelInboundHandlerAdapter {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(Check104Handler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf result = (ByteBuf) msg;
        byte[] bytes = new byte[result.readableBytes()];
        result.readBytes(bytes);
        logger.info("接收到的报文: "+ CommonByteUtils.BinaryToHexString(bytes));
        if (bytes.length< Iec104Constant.APCI_LENGTH || bytes[0]!=Iec104Constant.HEAD_DATA){
            logger.error("报文无效...");
            ReferenceCountUtil.release(result);
        }else {
            //重新将数据写入缓冲区
            result.writeBytes(bytes);
            ctx.fireChannelRead(msg);
        }
    }
}
