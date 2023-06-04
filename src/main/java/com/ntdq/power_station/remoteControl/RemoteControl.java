package com.ntdq.power_station.remoteControl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ntdq.power_station.remoteControl.vo.RemoteVO;
import com.ntdq.power_station.remoteControl.wrapper.PromiseWrapper;
import com.ntdq.power_station.domain.enumDo.DeviceEnum;
import com.ntdq.power_station.nettyHandler.tcp.init.client.Iec104ClientInitializer;
import com.ntdq.power_station.nettyHandler.tcp.master104.domain.MessageDetail;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 远程调控策略
 * 我需要每一次连接成功后 将我的ChannelPromise 和 一些回调函数注册进去
 */
@Component
public class RemoteControl extends AbstractVerticle {

    private static ChannelPromise controlCommandPromise;

    private static final Map<String, CountDownLatch> deviceBrokerMap = new ConcurrentHashMap<>();

    static {
        deviceBrokerMap.put(DeviceEnum.Energy.name(), new CountDownLatch(1));
    }

    @Override
    public void start() throws Exception {
        EventBus eventBus = vertx.eventBus();
        eventBus.consumer("Netty.method", message -> {
            JsonObject params = (JsonObject) message.body();
            JsonElement power = params.get("power");
            //TODO 执行方法返回返回结果
            startExecuteStrategy(power.getAsInt(), DeviceEnum.Energy);
        });
    }

    public RemoteVO startExecuteStrategy(int power, DeviceEnum deviceEnum) {
        RemoteVO remoteVO = null;
        try {
            remoteVO = sendPowerValueToDevice(power, deviceEnum, success -> {
                if (success) {
                    controlCommandPromise.setSuccess();
                } else {
                    controlCommandPromise.setFailure(new RuntimeException("遥控执行失败!"));
                }
            });
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return remoteVO;
    }


    /**
     * 功率修改策略 == 暂时就一个储能
     *
     * @param powerValue
     * @return
     */
    private RemoteVO sendPowerValueToDevice(int powerValue, DeviceEnum deviceEnum, ControlCommandCallback controlCommandCallback) throws InterruptedException {
        //拿到执行策略阻塞器
        PromiseWrapper channelPromiseWrapper = Iec104ClientInitializer.getClientHandler(deviceEnum).getChannelPromiseWrapper(controlCommandCallback);
        controlCommandPromise = channelPromiseWrapper.getChannelPromise();
        MessageDetail messageDetail = new MessageDetail(1, (short) 1);
//        ChannelFuture sendFuture =
        channelPromiseWrapper.getChannel().writeAndFlush(messageDetail);
//        sendFuture.addListener((ChannelFutureListener) future -> {
//            if (future.isSuccess()) {
//                // The control command was successfully sent
//                // You can update the controlCommandPromise with the success result
//                controlCommandPromise.setSuccess();
//            } else {
//                // The control command failed to send
//                // You can update the controlCommandPromise with the failure result
//                controlCommandPromise.setFailure(future.cause());
//            }
//        });
        controlCommandPromise.await();
        return RemoteVO.createResult(deviceEnum.name(), controlCommandPromise.isSuccess());
    }


    public Channel findChannelByName(DeviceEnum deviceEnum) {

        return null;
    }

    public static void main(String[] args) throws Exception {
//        RemoteControl remoteControl = new RemoteControl();
//        remoteControl.start();
        Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();
        VertxOptions options = new VertxOptions().setMaxEventLoopExecuteTime(Long.MAX_VALUE)
                .setMaxEventLoopExecuteTimeUnit(TimeUnit.DAYS)
                .setWarningExceptionTime(Long.MAX_VALUE)
                .setWarningExceptionTimeUnit(TimeUnit.DAYS)
                .setMaxWorkerExecuteTime(Long.MAX_VALUE)
                .setMaxWorkerExecuteTimeUnit(TimeUnit.DAYS)
                .setInternalBlockingPoolSize(200)
                .setBlockedThreadCheckInterval(1000)
                .setEventLoopPoolSize(10)
                .setWorkerPoolSize(20);

        httpServer.requestHandler(httpServerRequest -> {
            HttpServerResponse response = httpServerRequest.response();
            response.end("Hello world!");
        });

        httpServer.listen(8080);
        System.out.println("Server started on port 8080");
    }

    public static void main1(String[] args) throws InterruptedException {
        RemoteControl remoteControl = new RemoteControl();
        remoteControl.sendPowerValueToDevice(20, DeviceEnum.Energy, success -> {
            if (success) {
                controlCommandPromise.setSuccess();
            } else {
                controlCommandPromise.setFailure(new RuntimeException("遥控执行失败!"));
            }
        });
    }

    public static void main2(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {

                        }
                    });

            ChannelFuture future = bootstrap.connect("127.0.0.1", 9876);
            ChannelPromise channelPromise = future.channel().newPromise();
            new Thread(() -> {
                try {
                    System.out.println("开始阻塞...");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                channelPromise.setSuccess();
                System.out.println("结束阻塞...");
            }).start();
            channelPromise.await();
            System.out.println("结束");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            group.shutdownGracefully();
        }
    }


}
