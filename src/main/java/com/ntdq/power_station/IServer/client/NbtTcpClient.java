package com.ntdq.power_station.IServer.client;

import com.ntdq.power_station.IServer.IServer;
import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.config.netDomain.tcp.ModbusServerBOBase;
import com.ntdq.power_station.config.netDomain.tcp.Nbt33007BOBase;
import com.ntdq.power_station.socketStrategy.SocketProcess.ModBusClientProcess;
import com.ntdq.power_station.socketStrategy.SocketProcess.SocketContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

//@Component
public class NbtTcpClient extends IServer implements ApplicationContextAware, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ModBusNettyClient.class);

    private static ApplicationContext applicationContext;


    @Override
    public void startServer(ServerBaseConfiguration appConfiguration) {
        socketContext = new SocketContext(new ModBusClientProcess());
        socketContext.startNettyServer(appConfiguration);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Nbt33007BOBase nbt33007BOBase = applicationContext.getBean(Nbt33007BOBase.class);
        if (!nbt33007BOBase.isExistSocket("NBT33007-C")) {
            logger.info("未设置启用NBT33007-C服务...");
            return;
        }
        Thread thread = new Thread(() -> {
            startServer(nbt33007BOBase);
        });
        thread.setName("NBT33007-C");
        thread.start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        NbtTcpClient.applicationContext = applicationContext;
    }
}
