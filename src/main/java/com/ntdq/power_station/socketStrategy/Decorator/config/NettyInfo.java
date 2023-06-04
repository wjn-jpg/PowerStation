package com.ntdq.power_station.socketStrategy.Decorator.config;

import com.ntdq.power_station.socketStrategy.Decorator.config.NettyConfiguration;
import io.netty.bootstrap.AbstractBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

public class NettyInfo {

    protected AbstractBootstrap bootstrap;

    protected ChannelFuture channelFuture;


    protected Channel channel;

    private String host;

    private Integer port;

    private String serverName;

    public NettyInfo(Integer port) {
        this("127.0.0.1", port);
    }

    public NettyInfo(String host, Integer port) {
        this(host, port, null);
    }

    public NettyInfo(String host, Integer port, String serverName) {
        this.host = host;
        this.port = port;
        this.serverName = serverName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public AbstractBootstrap getBootstrap() {
        return bootstrap;
    }

    public void setBootstrap(AbstractBootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public ChannelFuture getChannelFuture() {
        return channelFuture;
    }

    public void setChannelFuture(ChannelFuture channelFuture) {
        this.channelFuture = channelFuture;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
