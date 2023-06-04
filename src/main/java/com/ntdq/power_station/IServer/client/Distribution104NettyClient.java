package com.ntdq.power_station.IServer.client;

import com.ntdq.power_station.IServer.IServer;
import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.config.netDomain.tcp.Distribution104ClientBOBase;
import com.ntdq.power_station.socketStrategy.SocketProcess.SocketContext;
import com.ntdq.power_station.socketStrategy.SocketProcess.Tcp104DistributionClientProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Distribution104NettyClient extends IServer implements InitializingBean, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(Distribution104NettyClient.class);

    private static ApplicationContext applicationContext;

    @Override
    public void startServer(ServerBaseConfiguration appConfiguration) {
        SocketContext socketContext = new SocketContext(new Tcp104DistributionClientProcess());
        socketContext.startNettyServer(appConfiguration);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Distribution104ClientBOBase distribution104ClientBOBase = applicationContext.getBean(Distribution104ClientBOBase.class);
        if (!distribution104ClientBOBase.isExistSocket("104Distribution")) {
            logger.info("未设置启用104变电客户端...");
            return;
        }
        //异步开启一个netty服务端
        Thread thread = new Thread(() -> {
            startServer(distribution104ClientBOBase);
        });
        thread.setName("TCP104DistributionClient");
        thread.start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Distribution104NettyClient.applicationContext = applicationContext;
    }
}
