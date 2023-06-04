package com.ntdq.power_station.IServer.server;

import com.ntdq.power_station.IServer.IServer;
import com.ntdq.power_station.config.mqtt.MqttServerBOBase;
import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.holder.mqtt.SessionSocketHolder;
import com.ntdq.power_station.socketStrategy.SocketProcess.MqttServerProcess;
import com.ntdq.power_station.socketStrategy.SocketProcess.SocketContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MqttNettyTcpServer extends IServer implements ApplicationContextAware, InitializingBean, MqttServerAdapter {


    private static final Logger logger = LoggerFactory.getLogger(MqttNettyTcpServer.class);

    private static ApplicationContext applicationContext;

    /**
     * mqtt服务是否已经启动
     */
    private static volatile boolean isStarted = false;

    @Override
    public void afterPropertiesSet() throws Exception {
//        System.out.println("....");
        MqttServerBOBase configuration = applicationContext.getBean(MqttServerBOBase.class);
        if (!configuration.isExistSocket("MQTT")) {
            logger.info("未设置启用MQTT服务...");
            return;
        }
        Thread thread = new Thread(() -> {
            startServer(configuration);
        });
        thread.setName("MQTTServer");
        configuration.setCurrentProcessThread(thread.getId());
        thread.start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MqttNettyTcpServer.applicationContext = applicationContext;
    }

    @Override
    public void startServer(ServerBaseConfiguration appConfiguration) {
        socketContext = new SocketContext(new MqttServerProcess());
        //SessionSocketHolder sessionSocketHolder = applicationContext.getBean(SessionSocketHolder.class);
        socketContext.startNettyServer(appConfiguration);
    }


    @Override
    public void sendAll(String topic, String sendMessage) {

    }

    public static boolean mqttIsStarted() {
        return isStarted;
    }

    public static void setMqttStarted(boolean isStarted) {
        MqttNettyTcpServer.isStarted = isStarted;
    }
}
