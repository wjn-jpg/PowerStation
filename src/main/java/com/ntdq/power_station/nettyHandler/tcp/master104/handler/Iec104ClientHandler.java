package com.ntdq.power_station.nettyHandler.tcp.master104.handler;

import com.ntdq.power_station.cache.NettyContext;
import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.remoteControl.ControlCommandCallback;
import com.ntdq.power_station.remoteControl.wrapper.PromiseWrapper;
import com.ntdq.power_station.nettyHandler.tcp.master104.constant.BasicInstruction104;
import com.ntdq.power_station.nettyHandler.tcp.master104.core.ControlSendAndRecCountPool;
import com.ntdq.power_station.nettyHandler.tcp.master104.core.Iec104ThreadLocal;
import com.ntdq.power_station.nettyHandler.tcp.master104.core.ScheduledTaskPool;
import com.ntdq.power_station.socketStrategy.Decorator.Expand.YcAndYxProcess;
import com.ntdq.power_station.util.CommonByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.ntdq.power_station.util.FeedConversionUtil.*;

public class Iec104ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private static final Logger logger = LoggerFactory.getLogger(Iec104ClientHandler.class);

    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private final ServerBaseConfiguration configuration;

    private YcAndYxProcess ycAndYxProcess;

    private Channel channel;

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Iec104ClientHandler(ServerBaseConfiguration configuration) {
        this.configuration = configuration;
    }

    public Iec104ClientHandler(ServerBaseConfiguration configuration, YcAndYxProcess ycAndYxProcess) {
        this.configuration = configuration;
        this.ycAndYxProcess = ycAndYxProcess;
    }

    public PromiseWrapper getChannelPromiseWrapper(ControlCommandCallback controlCommandCallback) {
        ChannelPromise channelPromise = channel.newPromise();
        return configuration.getParse().setControlPromiseWrapper(channel, channelPromise, controlCommandCallback);
    }

    public void setYcAndYxProcess(YcAndYxProcess ycAndYxProcess) {
        this.ycAndYxProcess = ycAndYxProcess;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //当104客户端成功连接上104服务端的时候一致发送启动链路命令
        logger.info("客户端连接上服务端");
        channel = ctx.channel();
        //连接服务后要先发送启动帧 让104服务端发送数据
        startSendFrame(ctx);
        //开启线程执行定时计算count发送任务
        startSendSFrame(ctx);
        //启动完成后 需要发送总召唤让服务端发送I帧数据
        //sendGeneralCallDetail104(ctx);
    }

    private void sendGeneralCallDetail104(ChannelHandlerContext ctx) {
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ctx.channel().writeAndFlush(BasicInstruction104.ALL);
        }).start();
    }


    private void startSendFrame(ChannelHandlerContext context) {
        //为这个线程副本附一个任务对象
        Iec104ThreadLocal.setScheduledTaskPool(new ScheduledTaskPool(context));
        Iec104ThreadLocal.getScheduledTaskPool().sendStartFrame();
    }

    private void startSendSFrame(ChannelHandlerContext context) {
        ControlSendAndRecCountPool controlSendAndRecCountPool = new ControlSendAndRecCountPool(context);
        System.out.println("controlSendAndRecCountPool C:" + controlSendAndRecCountPool);
        Iec104ThreadLocal.setControlSendAndRecCountPoolThreadLocal(controlSendAndRecCountPool);
        Iec104ThreadLocal.getControlSendAndRecCountPool().startSendFrameTask(configuration.getDeviceEnum());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        logger.info("拿到I帧数据");
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String s = CommonByteUtils.BinaryToHexString(bytes);
        logger.info("ChannelRead:{}", s);
        executorService.execute(new AnalysisByteTask(bytes, configuration, ycAndYxProcess));
        //需要频繁创建新线程的优先使用线程池
        NettyContext.addThreadPoolMapByNetty(configuration.getDeviceEnum().name(), executorService);
    }

    static class AnalysisByteTask implements Runnable {

        private final byte[] bytes;

        private final ServerBaseConfiguration configuration;

        private final YcAndYxProcess ycAndYxProcess;

        public AnalysisByteTask(byte[] bytes, ServerBaseConfiguration configuration) {
            this(bytes, configuration, null);
        }

        public AnalysisByteTask(byte[] bytes, ServerBaseConfiguration configuration, YcAndYxProcess ycAndYxProcess) {
            this.bytes = bytes;
            this.configuration = configuration;
            this.ycAndYxProcess = ycAndYxProcess;
        }

        @Override
        public void run() {
            configuration.getParse().parseByteToMessage(bytes, ycAndYxProcess);
        }
    }

    public static void main(String[] args) {
//        String hexStr2BinStr = hexStr2BinStr("8C");
//        System.out.println(hexStr2BinStr);
//        int dateNum = Integer.valueOf(hexStr2BinStr.substring(1, hexStr2BinStr.length()), 2);
//        System.out.println(dateNum);

        try {
            Float c01BA5E4 = Float.valueOf(toFloat("C02A7EFA"));
        } catch (NumberFormatException e) {
            System.out.println("出错!");
        }
        String dataN = "C02A7EFA";
        System.out.println(Float.intBitsToFloat(new BigInteger(dataN, 16).intValue()));
    }
}
