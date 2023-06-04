package com.ntdq.power_station.config.kafka;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn("mqttConfig")
public class KafkaConfig2Mqtt implements InitializingBean {


    @Autowired
    private MqttClient mqttClient;

    public void executeMessage2Mqtt() {

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(mqttClient);
    }

}
