package com.ntdq.power_station.IServer.server;

import com.ntdq.power_station.IServer.IServer;
import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.config.netDomain.tcp.PhotovoltaicServerBOBase;
import com.ntdq.power_station.socketStrategy.SocketProcess.SocketContext;
import com.ntdq.power_station.socketStrategy.SocketProcess.Tcp104PhotovoltaicServerProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Photovoltaic104NettyServer extends IServer implements InitializingBean, ApplicationContextAware {

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
        PhotovoltaicServerBOBase tcp104ServerBO = applicationContext.getBean(PhotovoltaicServerBOBase.class);
        if (!tcp104ServerBO.isExistSocket("104Photovoltaic")){
            logger.info("未设置启用104光伏服务端...");
            return;
        }
        //异步开启一个netty服务端
        Thread thread = new Thread(()->{
            startServer(tcp104ServerBO);
        });
        thread.setName("TCP104PhotovoltaicServer");
        thread.start();
        tcp104ServerBO.setCurrentThreadId(thread.getId());
    }

    @Override
    public void startServer(ServerBaseConfiguration appConfiguration) {
        socketContext = new SocketContext(new Tcp104PhotovoltaicServerProcess());
        socketContext.startNettyServer(appConfiguration);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Photovoltaic104NettyServer.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        PhotovoltaicServerBOBase iec104ClientBOBase = new PhotovoltaicServerBOBase();
        iec104ClientBOBase.setAddr104("192.168.0.74");
//        iec104ClientBOBase.setAddr104("127.0.0.1");
        iec104ClientBOBase.setPort104(2404);
        iec104ClientBOBase.setCurrentThreadId(Thread.currentThread().getId());
        Tcp104PhotovoltaicServerProcess tcp104ClientProcess = new Tcp104PhotovoltaicServerProcess();
        tcp104ClientProcess.startServer(iec104ClientBOBase);
    }
}
