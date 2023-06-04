package com.ntdq.power_station.nettyHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.constraints.NotNull;


public abstract class DefaultChannelHandler<T> extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(DefaultChannelHandler.class);

    protected abstract void read(ChannelHandlerContext context,T msg);

    @Override
    public void channelRead(ChannelHandlerContext ctx, @NotNull Object msg) throws Exception {
        this.read(ctx, (T) msg);
    }

//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        logger.info("{} 客户端与服务端连接...",ctx.channel().toString());
//    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("{} 客户端与服务端连接关闭...", ctx.channel().toString());
    }
}
