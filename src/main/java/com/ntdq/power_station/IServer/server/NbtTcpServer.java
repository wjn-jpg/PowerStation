package com.ntdq.power_station.IServer.server;

import com.ntdq.power_station.IServer.IServer;
import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.config.netDomain.tcp.Nbt33007BOBase;
import com.ntdq.power_station.socketStrategy.SocketProcess.Nbt33007ServerProcess;
import com.ntdq.power_station.socketStrategy.SocketProcess.SocketContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

//@Component
public class NbtTcpServer extends IServer implements ApplicationContextAware, InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(MqttNettyTcpServer.class);

    private static ApplicationContext applicationContext;


    @Override
    public void afterPropertiesSet() throws Exception {
        Nbt33007BOBase configuration = applicationContext.getBean(Nbt33007BOBase.class);
        if (!configuration.isExistSocket("NBT33007-S")) {
            logger.info("未设置启用NBT33007服务...");
            return;
        }
        Thread thread = new Thread(() -> {
            startServer(configuration);
        });
        thread.setName("NBT33007-S");
        thread.start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        NbtTcpServer.applicationContext = applicationContext;
    }

    @Override
    public void startServer(ServerBaseConfiguration appConfiguration) {
        socketContext = new SocketContext(new Nbt33007ServerProcess());
        socketContext.startNettyServer(appConfiguration);
    }

}
