package com.ntdq.power_station.config.kafka.Listener;

import com.alibaba.fastjson.JSON;
import com.ntdq.power_station.config.kafka.Entity.MqttMessageInfoStruct;
import com.ntdq.power_station.config.kafka.Entity.MqttMessageScreenVO;
import com.ntdq.power_station.config.kafka.VO.EnergyElectricityVO;
import com.ntdq.power_station.config.kafka.server.MessageServer;
import com.ntdq.power_station.domain.*;
import com.ntdq.power_station.domain.photovoltaic.yc.PhotovoltaicInverter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

//@Component
public class KafkaDeviceListenerGroup implements ApplicationRunner {

    private static final CyclicBarrier awaitLock = new CyclicBarrier(7);

    private static final Logger logger = LoggerFactory.getLogger(KafkaDeviceListenerGroup.class);

    //deviceid = key
    //atrCode = atrbCode
    private static final MqttMessageInfoStruct mqttMessageInfoStruct = new MqttMessageInfoStruct();

    private static final String TOPIC = "";
    @Autowired
    private MessageServer messageServer;

    @Autowired
    private MqttClient mqttClient;

    private void lockAndDecreaseCount() {
        try {
            awaitLock.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "Energy")
    public void consumerEnergy(ConsumerRecord<String, String> consumerRecord) {
        Optional<Object> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        if (kafkaMessage.isPresent()) {
            String message = (String) kafkaMessage.get();
            Energy energy = JSON.parseObject(message, Energy.class);
            //TODO ==> mysql redis ...
            mqttMessageInfoStruct.setEnergy(energy);
        }
        lockAndDecreaseCount();
    }

    @KafkaListener(topics = "Photovoltaic")
    public void consumerPhotovoltaic(ConsumerRecord<String, String> consumerRecord) {
        Optional<Object> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        if (kafkaMessage.isPresent()) {
            String message = (String) kafkaMessage.get();
            PhotovoltaicInverter photovoltaicInverter = JSON.parseObject(message, PhotovoltaicInverter.class);
            //TODO ==> photovoltaic
            mqttMessageInfoStruct.setPhotovoltaic(photovoltaicInverter);
        }
        //TODO
        lockAndDecreaseCount();
    }

    @KafkaListener(topics = "V2g")
    public void consumerV2g(ConsumerRecord<String, String> consumerRecord) {
        Optional<Object> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        if (kafkaMessage.isPresent()) {
            String message = (String) kafkaMessage.get();
            V2g v2g = JSON.parseObject(message, V2g.class);
            //TODO ==> V2g
            mqttMessageInfoStruct.setV2g(v2g);
        }
        //TODO
        lockAndDecreaseCount();
    }

    @KafkaListener(topics = "Distribution")
    public void consumerDistribution(ConsumerRecord<String, String> consumerRecord) {
        Optional<Object> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        if (kafkaMessage.isPresent()) {
            String message = (String) kafkaMessage.get();
            Distribution distribution = JSON.parseObject(message, Distribution.class);
            //TODO ==> Distribution
            mqttMessageInfoStruct.setDistribution(distribution);
        }
        //TODO
        lockAndDecreaseCount();
    }

    @KafkaListener(topics = "AirCondition")
    public void consumerAirCondition(ConsumerRecord<String, String> consumerRecord) {
        Optional<Object> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        if (kafkaMessage.isPresent()) {
            String message = (String) kafkaMessage.get();
            AirCondition airCondition = JSON.parseObject(message, AirCondition.class);
            mqttMessageInfoStruct.setAirCondition(airCondition);
        }
        //TODO
        lockAndDecreaseCount();
    }

    @KafkaListener(topics = "Lighting")
    public void consumerLighting(ConsumerRecord<String, String> consumerRecord) {
        Optional<Object> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        if (kafkaMessage.isPresent()) {
            String message = (String) kafkaMessage.get();
            Lighting lighting = JSON.parseObject(message, Lighting.class);
            mqttMessageInfoStruct.setLighting(lighting);
        }
        //TODO
        lockAndDecreaseCount();
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        while (true) {
            if (awaitLock.getNumberWaiting() == 6) {
                logger.info("处理所有数据中...");
                lockAndDecreaseCount();
                MqttMessageScreenVO mqttMessageScreenVO = messageServer.parseStructToScreenVO(mqttMessageInfoStruct);
                mqttClient.publish(TOPIC, JSON.toJSONString(mqttMessageScreenVO).getBytes(), 0, false);
            }
        }
    }

    public static void main1(String[] args) {
        MqttMessageScreenVO mqttMessageScreenVO = new MqttMessageScreenVO();
        mqttMessageScreenVO.setEnergyElectricityVO(new EnergyElectricityVO());
        String message = JSON.toJSONString(mqttMessageScreenVO);
        System.out.println(JSON.toJSONString(message));
        byte[] bytes = message.getBytes();
        System.out.println(new String(bytes));
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("123");
        strings.add("456");
        System.out.println(strings.toString());
    }
}
