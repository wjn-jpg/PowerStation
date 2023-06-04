package com.ntdq.power_station.IServer.client;

import com.ntdq.power_station.IServer.IServer;
import com.ntdq.power_station.IServer.server.Photovoltaic104NettyServer;
import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.config.netDomain.tcp.Energy104ClientBOBase;
import com.ntdq.power_station.socketStrategy.SocketProcess.SocketContext;
import com.ntdq.power_station.socketStrategy.SocketProcess.Tcp104EnergyClientProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Energy104NettyClient extends IServer implements InitializingBean, ApplicationContextAware {
    /**
     * 日志
     */
    private final static Logger logger = LoggerFactory.getLogger(Photovoltaic104NettyServer.class);

    /**
     * spring上下文
     */
    private static ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        Energy104ClientBOBase energy104ClientBOBase = applicationContext.getBean(Energy104ClientBOBase.class);
        if (!energy104ClientBOBase.isExistSocket("104Energy")){
            logger.info("未设置启用104储能客户端...");
            return;
        }
        //异步开启一个netty服务端
        Thread thread = new Thread(()->{
            startServer(energy104ClientBOBase);
        });
        thread.setName("TCP104EnergyClient");
        thread.start();
        energy104ClientBOBase.setCurrentThreadId(thread.getId());
    }

    @Override
    public void startServer(ServerBaseConfiguration appConfiguration) {
        socketContext = new SocketContext(new Tcp104EnergyClientProcess());
        socketContext.startNettyServer(appConfiguration);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Energy104NettyClient.applicationContext = applicationContext;
    }
}
