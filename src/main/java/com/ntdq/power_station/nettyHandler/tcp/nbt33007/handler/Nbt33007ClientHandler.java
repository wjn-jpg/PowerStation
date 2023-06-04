package com.ntdq.power_station.nettyHandler.tcp.nbt33007.handler;

import com.ntdq.power_station.nettyHandler.tcp.nbt33007.message.MessageDetail;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description : NBT33007 - Client端 - 业务处理器
 * @Author : Kang
 * @Date : 2023/4/19 16:44
 * @Version : 1.0
 */
public class Nbt33007ClientHandler extends SimpleChannelInboundHandler<MessageDetail> {

    private static final Logger log = LoggerFactory.getLogger(Nbt33007ClientHandler.class);

    /**
     * 当客户端完成连接服务器则触发该⽅法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        log.info("NBT33007客户端已连接到远程服务器：" + channel.remoteAddress());
    }

    /**
     * 读取服务端发送来的数据
     *
     * @param ctx
     * @param msg the message to handle
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageDetail msg) throws Exception {
        Channel channel = ctx.channel();
        log.info("读取服务端：" + channel.remoteAddress() + "发送来的数据：" + msg.toString());
    }

    /**
     * 出现异常时关闭通道
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        log.info("NBT33007服务端（Slave）：" + channel.remoteAddress() + "  异常！！！");
        cause.printStackTrace();
        ctx.close();
    }
}
