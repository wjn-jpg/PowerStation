package com.ntdq.power_station.domain.engrgy.yc;

/**
 * 南瑞保护装置YC
 */
public class NariProtectYC {

    /**
     * 南瑞保护1
     */
    private NariProtectDeviceYC nariProtectDeviceYC1;


    /**
     * 南瑞保护2
     */
    private NariProtectDeviceYC nariProtectDeviceYC2;


    public NariProtectDeviceYC getNariProtectDevice1() {
        return nariProtectDeviceYC1;
    }

    public void setNariProtectDevice1(NariProtectDeviceYC nariProtectDeviceYC1) {
        this.nariProtectDeviceYC1 = nariProtectDeviceYC1;
    }

    public NariProtectDeviceYC getNariProtectDevice2() {
        return nariProtectDeviceYC2;
    }

    public void setNariProtectDevice2(NariProtectDeviceYC nariProtectDeviceYC2) {
        this.nariProtectDeviceYC2 = nariProtectDeviceYC2;
    }
}
