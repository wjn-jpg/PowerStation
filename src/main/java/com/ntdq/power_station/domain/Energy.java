package com.ntdq.power_station.domain;

import com.ntdq.power_station.domain.engrgy.yc.EnergyInverter;
import com.ntdq.power_station.domain.engrgy.yc.NariProtectDeviceYC;

public class Energy {

    private EnergyInverter energyInverter1;

    private EnergyInverter energyInverter2;

    private NariProtectDeviceYC nariProtectDeviceYC1;

    private NariProtectDeviceYC nariProtectDeviceYC2;

    public EnergyInverter getEnergyInverter1() {
        return energyInverter1;
    }

    public void setEnergyInverter1(EnergyInverter energyInverter1) {
        this.energyInverter1 = energyInverter1;
    }

    public EnergyInverter getEnergyInverter2() {
        return energyInverter2;
    }

    public void setEnergyInverter2(EnergyInverter energyInverter2) {
        this.energyInverter2 = energyInverter2;
    }


    public NariProtectDeviceYC getNariProtectDeviceYC1() {
        return nariProtectDeviceYC1;
    }

    public void setNariProtectDeviceYC1(NariProtectDeviceYC nariProtectDeviceYC1) {
        this.nariProtectDeviceYC1 = nariProtectDeviceYC1;
    }

    public NariProtectDeviceYC getNariProtectDeviceYC2() {
        return nariProtectDeviceYC2;
    }

    public void setNariProtectDeviceYC2(NariProtectDeviceYC nariProtectDeviceYC2) {
        this.nariProtectDeviceYC2 = nariProtectDeviceYC2;
    }

    public static void main(String[] args) {
        System.out.println("这是唐博华写的实体类。。。");
    }

}
