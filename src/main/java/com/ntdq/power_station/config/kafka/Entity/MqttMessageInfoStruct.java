package com.ntdq.power_station.config.kafka.Entity;

import com.ntdq.power_station.domain.*;
import com.ntdq.power_station.domain.photovoltaic.yc.PhotovoltaicInverter;

public class MqttMessageInfoStruct {

    /**
     * 储能
     */
    private Energy energy;

    /**
     * 光伏
     */
    private PhotovoltaicInverter photovoltaicInverter;

    /**
     * 空调
     */
    private AirCondition airCondition;

    /**
     * 照明
     */
    private Lighting lighting;

    /**
     * v2g
     */
    private V2g v2g;

    /**
     * 变电
     */
    private Distribution distribution;


    public Energy getEnergy() {
        return energy;
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

    public PhotovoltaicInverter getPhotovoltaic() {
        return photovoltaicInverter;
    }

    public void setPhotovoltaic(PhotovoltaicInverter photovoltaicInverter) {
        this.photovoltaicInverter = photovoltaicInverter;
    }

    public AirCondition getAirCondition() {
        return airCondition;
    }

    public void setAirCondition(AirCondition airCondition) {
        this.airCondition = airCondition;
    }

    public Lighting getLighting() {
        return lighting;
    }

    public void setLighting(Lighting lighting) {
        this.lighting = lighting;
    }

    public V2g getV2g() {
        return v2g;
    }

    public void setV2g(V2g v2g) {
        this.v2g = v2g;
    }

    public Distribution getDistribution() {
        return distribution;
    }

    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
    }
}
