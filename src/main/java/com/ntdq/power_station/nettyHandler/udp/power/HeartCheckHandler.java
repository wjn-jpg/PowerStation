package com.ntdq.power_station.nettyHandler.udp.power;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务端心跳检测
 */
public class HeartCheckHandler extends ChannelDuplexHandler {

    private final static Logger logger = LoggerFactory.getLogger(HeartCheckHandler.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
             IdleStateEvent e = (IdleStateEvent) evt;
             if (e.state() == IdleState.READER_IDLE) {
                 logger.info("检测到服务端读空闲...");
//                 ctx.close();
//                 logger.info("关闭服务端通道...");
              } else if (e.state() == IdleState.WRITER_IDLE) {
                 logger.info("检测到服务端写空闲...");
//                  ctx.writeAndFlush(new PingMessage());
              }
          }
    }
}
