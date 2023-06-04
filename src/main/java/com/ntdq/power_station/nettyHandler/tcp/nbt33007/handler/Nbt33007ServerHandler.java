package com.ntdq.power_station.nettyHandler.tcp.nbt33007.handler;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.NBT33007SendMessageFactory;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.ReceiveMessageStrategy;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor.ReceiveMessageStrategyManager;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.message.MessageDetail;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.socketContext.ChannelManager;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description : NBT33007 - Server端 - 业务处理器
 * @Author : Kang
 * @Date : 2023/4/19 16:42
 * @Version : 1.0
 */

public class Nbt33007ServerHandler extends SimpleChannelInboundHandler<MessageDetail> {

    private final static Logger log = LoggerFactory.getLogger(Nbt33007ClientHandler.class);


    /**
     * 当客户端完成连接服务器则触发该⽅法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        log.error("NBT33007客户端：" + channel.remoteAddress() + "已连接...");
        ChannelManager.addChannel(channel.remoteAddress().toString(),channel);
    }

    /**
     * 关闭
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.error("通道已关闭...");
        Channel channel = ctx.channel();
        ChannelManager.removeChannel(channel.remoteAddress().toString());
    }

    /**
     * 读取客户端发送来的数据
     * @param ctx 含有Channel和pipeline的上下⽂对象
     * @param msg 客户端发送来的数据
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageDetail msg) throws Exception {
        if (msg.getStart() != 0x68){
            log.error("此报文不是NBT33007协议!!!");
            return;
        }
        Channel channel = ctx.channel();
        if (msg.getControlRegion()[0] != 0){
            //心跳报文...
            if (msg.getControlRegion()[0] == 0x43){
                MessageDetail messageDetail = NBT33007SendMessageFactory.masterSendHeartbeatConfirm(msg.getSourceAddress());
                channel.writeAndFlush(messageDetail);
                log.error("心跳报文!!!");
            }
        }else {
            //非心跳报文...
            ReceiveMessageStrategy messageStrategy = ReceiveMessageStrategyManager.getMessageStrategy(msg.getFrameType());
            if (messageStrategy != null){
                messageStrategy.receiveMessage(channel,msg);
            }else {
                log.error("此报文帧类型有误!!!");
            }
        }

        //log.info("读取客户端：" + channel.remoteAddress() + "发送来的数据：" + msg.toString());
    }

    /**
     * 服务端每次读完一次完整的数据之后，回调该方法，表示数据读取完毕。
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.error("报文已读完毕...");
        ctx.flush();
    }

    /**
     * 断开连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        log.error("NBT33007客户端：" + channel.remoteAddress() + "  已断开连接！！！");
    }

    /**
     * 出现异常时关闭通道
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        log.error("NBT33007客户端：" + channel.remoteAddress() + "  异常！！！");
        ChannelManager.removeChannel(channel.remoteAddress().toString());
        cause.printStackTrace();
        ctx.close();
    }

}
