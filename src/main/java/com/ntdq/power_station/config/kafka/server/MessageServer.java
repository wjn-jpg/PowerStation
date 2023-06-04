package com.ntdq.power_station.config.kafka.server;

import com.ntdq.power_station.config.kafka.Entity.MqttMessageInfoStruct;
import com.ntdq.power_station.config.kafka.Entity.MqttMessageScreenVO;

public interface MessageServer {

    MqttMessageScreenVO parseStructToScreenVO(MqttMessageInfoStruct mqttMessageInfoStruct);


}
