package com.ntdq.power_station.IController;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/mqtt/publish")
@RestController
public class MqttPublishController {

    /**
     * mqtt 默认发送主题
     */
    private static final String mqttTopic = "ntdq";

    private static final String mqttSendMessage = "testMessage";

//    @Autowired
    private MqttClient mqttClient;


    @RequestMapping("/sendMessage")
    public void sendMessage(String topic){
        try {
            if (mqttClient.isConnected()){
                byte[] bytes = mqttSendMessage.getBytes();
                MqttMessage mqttMessage = new MqttMessage();
                mqttMessage.setPayload(bytes);
                //需要ack返回 等待确认
//                mqttClient.publish(mqttTopic,mqttMessage);
                mqttClient.publish(topic, bytes,0,false); //不需要ack返回确认
            }
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }


}
