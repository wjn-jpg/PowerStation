package com.ntdq.power_station.config.kafka.server;

import com.ntdq.power_station.config.kafka.Entity.MqttMessageInfoStruct;
import com.ntdq.power_station.config.kafka.Entity.MqttMessageScreenVO;
import com.ntdq.power_station.domain.Energy;
import org.springframework.stereotype.Service;

@Service
public class MessageServerImpl implements MessageServer{

    @Override
    public MqttMessageScreenVO parseStructToScreenVO(MqttMessageInfoStruct mqttMessageInfoStruct) {
        Energy energy = mqttMessageInfoStruct.getEnergy();
        return null;
    }
}
