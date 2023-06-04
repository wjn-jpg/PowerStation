package com.ntdq.power_station;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan({"com.ntdq.power_station.mapper","com.ntdq.power_station.build.mapper"})
public class PowerStationApplication {
    public static void main(String[] args) {
        SpringApplication.run(PowerStationApplication.class, args);
    }

}
