package com.ntdq.power_station.IServer.server;

import com.ntdq.power_station.IServer.IServer;
import com.ntdq.power_station.config.netDomain.udp.PowerStationServerBOBase;
import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.socketStrategy.SocketProcess.SocketContext;
import com.ntdq.power_station.socketStrategy.SocketProcess.UdpServerProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class StationNettyUdpServer extends IServer implements InitializingBean, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(StationNettyUdpServer.class);

    /**
     * spring上下文
     */
    private static ApplicationContext applicationContext;

    /**
     * 当前启动netty服务的线程
     */
    private long currentStartThread;

    /**
     * 服务是否存活
     */
    private boolean isKeepAlive;


    public void startServer(ServerBaseConfiguration appConfiguration){
        socketContext = new SocketContext(new UdpServerProcess());
        socketContext.startNettyServer(appConfiguration);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        PowerStationServerBOBase configuration = applicationContext.getBean(PowerStationServerBOBase.class);
        if (!configuration.isExistSocket("UDP")){
            logger.info("未设置启用UDP服务...");
            return;
        }
        //异步开启一个netty服务端
       Thread thread = new Thread(()->{
           startServer(configuration);
       });
       thread.setName("UDPStationServer");
       thread.start();
       configuration.setCurrentThreadId(thread.getId());
       isKeepAlive = true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        StationNettyUdpServer.applicationContext = applicationContext;
    }

}
