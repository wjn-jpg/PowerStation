package com.ntdq.power_station.IServer.server;

public interface MqttServerAdapter {

    /**
     * 发送数据给对应的主题
     * @param topic
     * @param sendMessage
     */
    void sendAll(String topic,String sendMessage);


}
