package com.ntdq.power_station.nettyHandler.tcp.init.server;

//public class Tcp104ServerHandlerInitializer extends ChannelInitializer<NioServerSocketChannel> {
//    @Override
//    protected void initChannel(NioServerSocketChannel socketChannel) throws Exception {
//        ChannelPipeline pipeline = socketChannel.pipeline();
//        pipeline.addLast("unpack",new Tcp104ServerHandler());
//        pipeline.addLast("check",new Check104Handler());
//        pipeline.addLast("uFrame",new SysUFrameClientHandler());
//        pipeline.addLast("sFrame",new SysSFrameClientHandler());
//        pipeline.addLast("clientHandler",new Iec104ClientHandler());
//    }

//}
