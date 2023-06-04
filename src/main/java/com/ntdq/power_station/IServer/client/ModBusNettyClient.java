package com.ntdq.power_station.IServer.client;

import com.ntdq.power_station.IServer.IServer;
import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.config.netDomain.tcp.ModbusServerBOBase;
import com.ntdq.power_station.socketStrategy.SocketProcess.ModBusClientProcess;
import com.ntdq.power_station.socketStrategy.SocketProcess.SocketContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ModBusNettyClient extends IServer {

    private static final Logger logger = LoggerFactory.getLogger(ModBusNettyClient.class);

    private static ApplicationContext applicationContext;

    @Override
    public void startServer(ServerBaseConfiguration appConfiguration) {
        socketContext = new SocketContext(new ModBusClientProcess());
        socketContext.startNettyServer(appConfiguration);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ModbusServerBOBase modbusServerBOBase = applicationContext.getBean(ModbusServerBOBase.class);
        if (!modbusServerBOBase.isExistSocket("modbus")) {
            logger.info("未设置启用modbus服务...");
            return;
        }
        Thread thread = new Thread(() -> {
            startServer(modbusServerBOBase);
        });
        thread.setName("ModBusServer");
        modbusServerBOBase.setCurrentThreadId(thread.getId());
        thread.start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ModBusNettyClient.applicationContext = applicationContext;
    }
}
