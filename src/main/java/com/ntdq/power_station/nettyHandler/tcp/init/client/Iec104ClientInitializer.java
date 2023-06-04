package com.ntdq.power_station.nettyHandler.tcp.init.client;

import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.domain.enumDo.DeviceEnum;
import com.ntdq.power_station.nettyHandler.tcp.master104.handler.*;
import com.ntdq.power_station.socketStrategy.Decorator.Expand.YcAndYxProcess;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Iec104ClientInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * 保存所有的ClientHandler对象Map
     */
    private static final Map<String, Iec104ClientHandler> deviceIecHandler = new ConcurrentHashMap<>();

    private ServerBaseConfiguration configuration;

    private YcAndYxProcess ycAndYxProcess;

    private Iec104ClientHandler iec104ClientHandler;

    public static Iec104ClientHandler getClientHandler(DeviceEnum deviceEnum) {
        return deviceIecHandler.get(deviceEnum.name());
    }

    public Iec104ClientInitializer() {

    }

    public void putDeviceClientHandlerManual(ServerBaseConfiguration configuration) {
        this.iec104ClientHandler = new Iec104ClientHandler(configuration);
        deviceIecHandler.put(configuration.getDeviceEnum().name(), iec104ClientHandler);
    }

    public Iec104ClientInitializer(ServerBaseConfiguration configuration) {
        this.configuration = configuration;
        this.iec104ClientHandler = new Iec104ClientHandler(configuration);
        deviceIecHandler.put(configuration.getDeviceEnum().name(), iec104ClientHandler);
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("unpack", new Unpack104Handler());
        pipeline.addLast("check", new Check104Handler());
        pipeline.addLast("uFrame", new SysUFrameClientHandler());
        pipeline.addLast("sFrame", new SysSFrameClientHandler());
//        pipeline.addLast("messageDetail",new MessageDetail104());
        pipeline.addLast("byteEncoder", new BytesEncoder());
        pipeline.addLast("messageDetailEncoder", new MessageDetailEncoder());
        if (ycAndYxProcess == null) {
            pipeline.addLast("clientHandler", iec104ClientHandler);
        } else {
            iec104ClientHandler.setYcAndYxProcess(ycAndYxProcess);
            pipeline.addLast("clientHandler", iec104ClientHandler);
        }
    }

    public void setConfiguration(ServerBaseConfiguration configuration) {
        this.configuration = configuration;
    }

    public void setYcAndYxProcess(YcAndYxProcess ycAndYxProcess) {
        this.ycAndYxProcess = ycAndYxProcess;
    }
}
