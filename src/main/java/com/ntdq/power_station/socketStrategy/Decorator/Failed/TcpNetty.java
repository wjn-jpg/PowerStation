package com.ntdq.power_station.socketStrategy.Decorator.Failed;

import io.netty.bootstrap.AbstractBootstrap;

/**
 * Tcp 客户端 Netty需要哪些组件
 */
public interface TcpNetty {

    AbstractBootstrap generateBootStrap();

}
