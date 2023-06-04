package com.ntdq.power_station.config.kafka.Topic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class KafkaTopicConfig {


    @Bean
    public NewTopic batchTopicV2g(){
        return new NewTopic("V2g",1, (short) 1);
    }

    @Bean
    public NewTopic batchTopicEnergy(){
        return new NewTopic("Energy",1, (short) 1);
    }

    @Bean
    public NewTopic batchTopicLighting(){
        return new NewTopic("Lighting",1, (short) 1);
    }

    @Bean
    public NewTopic batchTopicPhotovoltaic(){
        return new NewTopic("Photovoltaic",1, (short) 1);
    }

    @Bean
    public NewTopic batchTopicAirCondition(){
        return new NewTopic("AirCondition",1, (short) 1);
    }

    @Bean
    public NewTopic batchTopicDistribution(){
        return new NewTopic("Distribution",1, (short) 1);
    }

}
