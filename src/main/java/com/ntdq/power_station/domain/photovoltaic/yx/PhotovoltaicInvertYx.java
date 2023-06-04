package com.ntdq.power_station.domain.photovoltaic.yx;

public class PhotovoltaicInvertYx {
    /**
     * 是否并网
     */
    private int Gird_connection;

    /**
     * 是否人为关机
     */
    private int Manual_shutdown;

    /**
     * 是否PV过压
     */
    private int PV_over;

    /**
     * 是否欠压
     */
    private int PV_under;

    /**
     * 是否电网电压低
     */
    private int Low_gridVoltage;

    /**
     * 是否电网电压高
     */
    private int High_gridVoltage;

    /**
     * 电网频率低
     */
    private int Low_gridFrequency;

    /**
     * 电网频率高
     */
    private int High_gridFrequency;

    /**
     * 输出过流
     */
    private int Output_overCurrent;

    /**
     * 过温关机
     */
    private int Over_temperatureShutdown;

    /**
     * 直流分量保护
     */
    private int DCComponentProtection;

    /**
     * 漏电流保护
     */
    private int LeakageCurrentProtection;

    /**
     * 孤岛保护
     */
    private int LandProtection;

    /**
     * 母线过压保护
     */
    private int BusOverVoltageProtection;

    /**
     * 12v供电异常
     */
    private int Abnormal12VPowerSupply;

    /**
     * 谐振关机
     */
    private int ResonantShutdown;

    /**
     * 受控
     */
    private int Control;

    /**
     * 是否正常
     */
    private int Normal;

    /**
     * 是否待机
     */
    private int Wait;

    /**
     * 是否故障
     */
    private int Hitch;

    /**
     * PV1过压
     */
    private int PV1_overVoltage;

    /**
     * PV2过压
     */
    private int PV2_overVoltage;

    /**
     * PV3过压
     */
    private int PV3_overVoltage;

    /**
     * PV4过压
     */
    private int PV4_overVoltage;

    /**
     * PV1欠压
     */
    private int PV1_underVoltage;

    /**
     * PV2欠压
     */
    private int PV2_underVoltage;

    /**
     * PV3欠压
     */
    private int PV3_underVoltage;

    /**
     * PV4欠压
     */
    private int PV4_underVoltage;

    public int getGird_connection() {
        return Gird_connection;
    }

    public void setGird_connection(int gird_connection) {
        Gird_connection = gird_connection;
    }

    public int getManual_shutdown() {
        return Manual_shutdown;
    }

    public void setManual_shutdown(int manual_shutdown) {
        Manual_shutdown = manual_shutdown;
    }

    public int getPV_over() {
        return PV_over;
    }

    public void setPV_over(int PV_over) {
        this.PV_over = PV_over;
    }

    public int getPV_under() {
        return PV_under;
    }

    public void setPV_under(int PV_under) {
        this.PV_under = PV_under;
    }

    public int getLow_gridVoltage() {
        return Low_gridVoltage;
    }

    public void setLow_gridVoltage(int low_gridVoltage) {
        Low_gridVoltage = low_gridVoltage;
    }

    public int getHigh_gridVoltage() {
        return High_gridVoltage;
    }

    public void setHigh_gridVoltage(int high_gridVoltage) {
        High_gridVoltage = high_gridVoltage;
    }

    public int getLow_gridFrequency() {
        return Low_gridFrequency;
    }

    public void setLow_gridFrequency(int low_gridFrequency) {
        Low_gridFrequency = low_gridFrequency;
    }

    public int getHigh_gridFrequency() {
        return High_gridFrequency;
    }

    public void setHigh_gridFrequency(int high_gridFrequency) {
        High_gridFrequency = high_gridFrequency;
    }

    public int getOutput_overCurrent() {
        return Output_overCurrent;
    }

    public void setOutput_overCurrent(int output_overCurrent) {
        Output_overCurrent = output_overCurrent;
    }

    public int getOver_temperatureShutdown() {
        return Over_temperatureShutdown;
    }

    public void setOver_temperatureShutdown(int over_temperatureShutdown) {
        Over_temperatureShutdown = over_temperatureShutdown;
    }

    public int getDCComponentProtection() {
        return DCComponentProtection;
    }

    public void setDCComponentProtection(int DCComponentProtection) {
        this.DCComponentProtection = DCComponentProtection;
    }

    public int getLeakageCurrentProtection() {
        return LeakageCurrentProtection;
    }

    public void setLeakageCurrentProtection(int leakageCurrentProtection) {
        LeakageCurrentProtection = leakageCurrentProtection;
    }

    public int getLandProtection() {
        return LandProtection;
    }

    public void setLandProtection(int landProtection) {
        LandProtection = landProtection;
    }

    public int getBusOverVoltageProtection() {
        return BusOverVoltageProtection;
    }

    public void setBusOverVoltageProtection(int busOverVoltageProtection) {
        BusOverVoltageProtection = busOverVoltageProtection;
    }

    public int getAbnormal12VPowerSupply() {
        return Abnormal12VPowerSupply;
    }

    public void setAbnormal12VPowerSupply(int abnormal12VPowerSupply) {
        Abnormal12VPowerSupply = abnormal12VPowerSupply;
    }

    public int getResonantShutdown() {
        return ResonantShutdown;
    }

    public void setResonantShutdown(int resonantShutdown) {
        ResonantShutdown = resonantShutdown;
    }

    public int getControl() {
        return Control;
    }

    public void setControl(int control) {
        Control = control;
    }

    public int getNormal() {
        return Normal;
    }

    public void setNormal(int normal) {
        Normal = normal;
    }

    public int getWait() {
        return Wait;
    }

    public void setWait(int wait) {
        Wait = wait;
    }

    public int getHitch() {
        return Hitch;
    }

    public void setHitch(int hitch) {
        Hitch = hitch;
    }

    public int getPV1_overVoltage() {
        return PV1_overVoltage;
    }

    public void setPV1_overVoltage(int PV1_overVoltage) {
        this.PV1_overVoltage = PV1_overVoltage;
    }

    public int getPV2_overVoltage() {
        return PV2_overVoltage;
    }

    public void setPV2_overVoltage(int PV2_overVoltage) {
        this.PV2_overVoltage = PV2_overVoltage;
    }

    public int getPV3_overVoltage() {
        return PV3_overVoltage;
    }

    public void setPV3_overVoltage(int PV3_overVoltage) {
        this.PV3_overVoltage = PV3_overVoltage;
    }

    public int getPV4_overVoltage() {
        return PV4_overVoltage;
    }

    public void setPV4_overVoltage(int PV4_overVoltage) {
        this.PV4_overVoltage = PV4_overVoltage;
    }

    public int getPV1_underVoltage() {
        return PV1_underVoltage;
    }

    public void setPV1_underVoltage(int PV1_underVoltage) {
        this.PV1_underVoltage = PV1_underVoltage;
    }

    public int getPV2_underVoltage() {
        return PV2_underVoltage;
    }

    public void setPV2_underVoltage(int PV2_underVoltage) {
        this.PV2_underVoltage = PV2_underVoltage;
    }

    public int getPV3_underVoltage() {
        return PV3_underVoltage;
    }

    public void setPV3_underVoltage(int PV3_underVoltage) {
        this.PV3_underVoltage = PV3_underVoltage;
    }

    public int getPV4_underVoltage() {
        return PV4_underVoltage;
    }

    public void setPV4_underVoltage(int PV4_underVoltage) {
        this.PV4_underVoltage = PV4_underVoltage;
    }
}
