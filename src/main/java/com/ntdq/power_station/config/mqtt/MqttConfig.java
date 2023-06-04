package com.ntdq.power_station.config.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@DependsOn({"stationNettyUdpServer","mqttNettyTcpServer","photovoltaic104NettyServer"})
public class MqttConfig {

    @Autowired
    private MqttClientProperties mqttClientProperties;

    @Autowired
    private MqttConsumerCallback mqttConsumerCallback;

    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setConnectionTimeout(mqttClientProperties.getCompletionTimeout());
        mqttConnectOptions.setKeepAliveInterval(mqttClientProperties.getKeepAliveInterval());
        mqttConnectOptions.setCleanSession(false);
        return mqttConnectOptions;
    }

    @Bean
    public MqttClient getMqttClient() {
        MqttClient mqttClient = null;
        try {
            mqttClient = new MqttClient(mqttClientProperties.getUrl(), mqttClientProperties.getClientId(), new MemoryPersistence());
            mqttClient.setCallback(mqttConsumerCallback);
            return mqttClient;
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

}
