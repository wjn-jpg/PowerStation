package com.ntdq.power_station.IServer.client;

import com.ntdq.power_station.IServer.server.MqttNettyTcpServer;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class MqttClientStart implements InitializingBean {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(MqttClientStart.class);

    @Autowired
    private MqttClient mqttClient;

    @Autowired
    private MqttConnectOptions mqttConnectOptions;


    @Override
    public void afterPropertiesSet() throws Exception {

        Thread startClientMqtt = new Thread(() -> {
            try {
                for (; ; ) {
                    if (MqttNettyTcpServer.mqttIsStarted()) {
                        try {
                            logger.info("准备连接Mqtt客户端!");
                            mqttClient.connect(mqttConnectOptions);
                            break;
                        } catch (MqttException e) {
                            logger.info("mqtt客户端连接失败！");
                        }
                    } else {
                        logger.info("Mqtt未启动 等待...");
                        Thread.sleep(1000);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        startClientMqtt.setName("SendMqttMessageClient");
        startClientMqtt.start();
    }
}
