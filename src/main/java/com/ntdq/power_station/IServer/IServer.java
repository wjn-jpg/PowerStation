package com.ntdq.power_station.IServer;

import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.socketStrategy.SocketProcess.SocketContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContextAware;

public abstract class IServer implements ApplicationContextAware, InitializingBean {
    /**
     * sockContext上下文
     */
    protected SocketContext socketContext;

   public abstract void startServer(ServerBaseConfiguration appConfiguration);

}
