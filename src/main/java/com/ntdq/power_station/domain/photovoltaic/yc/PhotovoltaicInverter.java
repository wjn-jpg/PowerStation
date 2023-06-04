package com.ntdq.power_station.domain.photovoltaic.yc;

/**
 * 光伏点表实体类
 */
public class PhotovoltaicInverter {

    /**
     * DC电压
     */
    private float DC_voltage;

    /**
     * DC电流
     */
    private float DC_current;

    /**
     * DC功率
     */
    private float DC_power;

    /**
     * 电网AB线电压
     */
    private float AB_voltage;

    /**
     * 电网BC线电压
     */
    private float BC_voltage;

    /**
     * 电网CA线电压
     */
    private float CA_voltage;

    /**
     * A相电压
     */
    private float A_voltage;

    /**
     * B相电压
     */
    private float B_voltage;

    /**
     * C相电压
     */
    private float C_voltage;

    /**
     * A相并网电流
     */
    private float A_net_current;

    /**
     * B相并网电流
     */
    private float B_net_current;

    /**
     * C相并网电流
     */
    private float C_net_current;

    /**
     * 电网频率
     */
    private float grid_frequency;

    /**
     * 有功功率
     */
    private float active_power;

    /**
     * 视在功率
     */
    private float apparent_power;

    /**
     * 无功功率
     */
    private float reactive_power;

    /**
     * 功率因素
     */
    private float power_factor;

    /**
     * 机箱内温度
     */
    private float temperature_cabinet;

    /**
     * 总发电量
     */
    private float gross_generation;

    /**
     * 日发电量
     */
    private float day_generation;

    /**
     * 支路1输出电压
     */
    private float branch1_output_voltage;

    /**
     * 支路1输出电流
     */
    private float branch1_output_current;

    /**
     * 支路2输出电压
     */
    private float branch2_output_voltage;

    /**
     * 支路2输出电流
     */
    private float branch2_output_current;

    /**
     * 支路3输出电压
     */
    private float branch3_output_voltage;

    /**
     * 支路3输出电流
     */
    private float branch3_output_current;

    /**
     * 支路4输出电压
     */
    private float branch4_output_voltage;

    /**
     * 支路4输出电流
     */
    private float branch4_output_current;

    /**
     * 支路5输出电压
     */
    private float branch5_output_voltage;

    /**
     * 支路5输出电流
     */
    private float branch5_output_current;

    /**
     * 支路6输出电压
     */
    private float branch6_output_voltage;

    /**
     * 支路6输出电流
     */
    private float branch6_output_current;

    /**
     * 支路7输出电压
     */
    private float branch7_output_voltage;

    /**
     * 支路7输出电流
     */
    private float branch7_output_current;

    /**
     * 支路8输出电压
     */
    private float branch8_output_voltage;

    /**
     * 支路8输出电流
     */
    private float branch8_output_current;

    /**
     * MPPT1电压
     */
    private float voltage_MPPT1;

    /**
     * MPPT2电压
     */
    private float voltage_MPPT2;

    /**
     * MPPT3电压
     */
    private float voltage_MPPT3;

    /**
     * MPPT4电压
     */
    private float voltage_MPPT4;

    //=========================================================以上为逆变器遥测信息
    //=========================================================以上为逆变器遥信信息

    /**
     * 是否并网
     */
    private boolean isGird_connection;

    /**
     * 是否人为关机
     */
    private boolean isManual_shutdown;

    /**
     * 是否PV过压
     */
    private boolean isPV_over;

    /**
     * 是否欠压
     */
    private boolean isPV_under;

    /**
     * 是否电网电压低
     */
    private boolean isLow_gridVoltage;

    /**
     * 是否电网电压高
     */
    private boolean isHigh_gridVoltage;

    /**
     * 电网频率低
     */
    private boolean isLow_gridFrequency;

    /**
     * 电网频率高
     */
    private boolean isHigh_gridFrequency;

    /**
     * 输出过流
     */
    private boolean isOutput_overCurrent;

    /**
     * 过温关机
     */
    private boolean isOver_temperatureShutdown;

    /**
     * 直流分量保护
     */
    private boolean isDCComponentProtection;

    /**
     * 漏电流保护
     */
    private boolean isLeakageCurrentProtection;

    /**
     * 孤岛保护
     */
    private boolean isLandProtection;

    /**
     * 母线过压保护
     */
    private boolean isBusOverVoltageProtection;

    /**
     * 12v供电异常
     */
    private boolean isAbnormal12VPowerSupply;

    /**
     * 谐振关机
     */
    private boolean isResonantShutdown;

    /**
     * 受控
     */
    private boolean isControl;

    /**
     * 是否正常
     */
    private boolean isNormal;

    /**
     * 是否待机
     */
    private boolean isWait;

    /**
     * 是否故障
     */
    private boolean isHitch;

    /**
     * PV1过压
     */
    private boolean isPV1_overVoltage;

    /**
     * PV2过压
     */
    private boolean isPV2_overVoltage;

    /**
     * PV3过压
     */
    private boolean isPV3_overVoltage;

    /**
     * PV4过压
     */
    private boolean isPV4_overVoltage;

    /**
     * PV1欠压
     */
    private boolean isPV1_underVoltage;

    /**
     * PV2欠压
     */
    private boolean isPV2_underVoltage;

    /**
     * PV3欠压
     */
    private boolean isPV3_underVoltage;

    /**
     * PV4欠压
     */
    private boolean isPV4_underVoltage;

    public float getDC_voltage() {
        return DC_voltage;
    }

    public void setDC_voltage(float DC_voltage) {
        this.DC_voltage = DC_voltage;
    }

    public float getDC_current() {
        return DC_current;
    }

    public void setDC_current(float DC_current) {
        this.DC_current = DC_current;
    }

    public float getDC_power() {
        return DC_power;
    }

    public void setDC_power(float DC_power) {
        this.DC_power = DC_power;
    }

    public float getAB_voltage() {
        return AB_voltage;
    }

    public void setAB_voltage(float AB_voltage) {
        this.AB_voltage = AB_voltage;
    }

    public float getBC_voltage() {
        return BC_voltage;
    }

    public void setBC_voltage(float BC_voltage) {
        this.BC_voltage = BC_voltage;
    }

    public float getCA_voltage() {
        return CA_voltage;
    }

    public void setCA_voltage(float CA_voltage) {
        this.CA_voltage = CA_voltage;
    }

    public float getA_voltage() {
        return A_voltage;
    }

    public void setA_voltage(float a_voltage) {
        A_voltage = a_voltage;
    }

    public float getB_voltage() {
        return B_voltage;
    }

    public void setB_voltage(float b_voltage) {
        B_voltage = b_voltage;
    }

    public float getC_voltage() {
        return C_voltage;
    }

    public void setC_voltage(float c_voltage) {
        C_voltage = c_voltage;
    }

    public float getA_net_current() {
        return A_net_current;
    }

    public void setA_net_current(float a_net_current) {
        A_net_current = a_net_current;
    }

    public float getB_net_current() {
        return B_net_current;
    }

    public void setB_net_current(float b_net_current) {
        B_net_current = b_net_current;
    }

    public float getC_net_current() {
        return C_net_current;
    }

    public void setC_net_current(float c_net_current) {
        C_net_current = c_net_current;
    }

    public float getGrid_frequency() {
        return grid_frequency;
    }

    public void setGrid_frequency(float grid_frequency) {
        this.grid_frequency = grid_frequency;
    }

    public float getActive_power() {
        return active_power;
    }

    public void setActive_power(float active_power) {
        this.active_power = active_power;
    }

    public float getApparent_power() {
        return apparent_power;
    }

    public void setApparent_power(float apparent_power) {
        this.apparent_power = apparent_power;
    }

    public float getReactive_power() {
        return reactive_power;
    }

    public void setReactive_power(float reactive_power) {
        this.reactive_power = reactive_power;
    }

    public float getPower_factor() {
        return power_factor;
    }

    public void setPower_factor(float power_factor) {
        this.power_factor = power_factor;
    }

    public float getTemperature_cabinet() {
        return temperature_cabinet;
    }

    public void setTemperature_cabinet(float temperature_cabinet) {
        this.temperature_cabinet = temperature_cabinet;
    }

    public float getGross_generation() {
        return gross_generation;
    }

    public void setGross_generation(float gross_generation) {
        this.gross_generation = gross_generation;
    }

    public float getDay_generation() {
        return day_generation;
    }

    public void setDay_generation(float day_generation) {
        this.day_generation = day_generation;
    }

    public float getBranch1_output_voltage() {
        return branch1_output_voltage;
    }

    public void setBranch1_output_voltage(float branch1_output_voltage) {
        this.branch1_output_voltage = branch1_output_voltage;
    }

    public float getBranch1_output_current() {
        return branch1_output_current;
    }

    public void setBranch1_output_current(float branch1_output_current) {
        this.branch1_output_current = branch1_output_current;
    }

    public float getBranch2_output_voltage() {
        return branch2_output_voltage;
    }

    public void setBranch2_output_voltage(float branch2_output_voltage) {
        this.branch2_output_voltage = branch2_output_voltage;
    }

    public float getBranch2_output_current() {
        return branch2_output_current;
    }

    public void setBranch2_output_current(float branch2_output_current) {
        this.branch2_output_current = branch2_output_current;
    }

    public float getBranch3_output_voltage() {
        return branch3_output_voltage;
    }

    public void setBranch3_output_voltage(float branch3_output_voltage) {
        this.branch3_output_voltage = branch3_output_voltage;
    }

    public float getBranch3_output_current() {
        return branch3_output_current;
    }

    public void setBranch3_output_current(float branch3_output_current) {
        this.branch3_output_current = branch3_output_current;
    }

    public float getBranch4_output_voltage() {
        return branch4_output_voltage;
    }

    public void setBranch4_output_voltage(float branch4_output_voltage) {
        this.branch4_output_voltage = branch4_output_voltage;
    }

    public float getBranch4_output_current() {
        return branch4_output_current;
    }

    public void setBranch4_output_current(float branch4_output_current) {
        this.branch4_output_current = branch4_output_current;
    }

    public float getBranch5_output_voltage() {
        return branch5_output_voltage;
    }

    public void setBranch5_output_voltage(float branch5_output_voltage) {
        this.branch5_output_voltage = branch5_output_voltage;
    }

    public float getBranch5_output_current() {
        return branch5_output_current;
    }

    public void setBranch5_output_current(float branch5_output_current) {
        this.branch5_output_current = branch5_output_current;
    }

    public float getBranch6_output_voltage() {
        return branch6_output_voltage;
    }

    public void setBranch6_output_voltage(float branch6_output_voltage) {
        this.branch6_output_voltage = branch6_output_voltage;
    }

    public float getBranch6_output_current() {
        return branch6_output_current;
    }

    public void setBranch6_output_current(float branch6_output_current) {
        this.branch6_output_current = branch6_output_current;
    }

    public float getBranch7_output_voltage() {
        return branch7_output_voltage;
    }

    public void setBranch7_output_voltage(float branch7_output_voltage) {
        this.branch7_output_voltage = branch7_output_voltage;
    }

    public float getBranch7_output_current() {
        return branch7_output_current;
    }

    public void setBranch7_output_current(float branch7_output_current) {
        this.branch7_output_current = branch7_output_current;
    }

    public float getBranch8_output_voltage() {
        return branch8_output_voltage;
    }

    public void setBranch8_output_voltage(float branch8_output_voltage) {
        this.branch8_output_voltage = branch8_output_voltage;
    }

    public float getBranch8_output_current() {
        return branch8_output_current;
    }

    public void setBranch8_output_current(float branch8_output_current) {
        this.branch8_output_current = branch8_output_current;
    }

    public float getVoltage_MPPT1() {
        return voltage_MPPT1;
    }

    public void setVoltage_MPPT1(float voltage_MPPT1) {
        this.voltage_MPPT1 = voltage_MPPT1;
    }

    public float getVoltage_MPPT2() {
        return voltage_MPPT2;
    }

    public void setVoltage_MPPT2(float voltage_MPPT2) {
        this.voltage_MPPT2 = voltage_MPPT2;
    }

    public float getVoltage_MPPT3() {
        return voltage_MPPT3;
    }

    public void setVoltage_MPPT3(float voltage_MPPT3) {
        this.voltage_MPPT3 = voltage_MPPT3;
    }

    public float getVoltage_MPPT4() {
        return voltage_MPPT4;
    }

    public void setVoltage_MPPT4(float voltage_MPPT4) {
        this.voltage_MPPT4 = voltage_MPPT4;
    }

    //====================以上是遥测的set/get方法
    //====================以下是遥信的set/get方法


    public boolean isGird_connection() {
        return isGird_connection;
    }

    public void setGird_connection(boolean gird_connection) {
        isGird_connection = gird_connection;
    }

    public boolean isManual_shutdown() {
        return isManual_shutdown;
    }

    public void setManual_shutdown(boolean manual_shutdown) {
        isManual_shutdown = manual_shutdown;
    }

    public boolean isPV_over() {
        return isPV_over;
    }

    public void setPV_over(boolean PV_over) {
        isPV_over = PV_over;
    }

    public boolean isPV_under() {
        return isPV_under;
    }

    public void setPV_under(boolean PV_under) {
        isPV_under = PV_under;
    }

    public boolean isLow_gridVoltage() {
        return isLow_gridVoltage;
    }

    public void setLow_gridVoltage(boolean low_gridVoltage) {
        isLow_gridVoltage = low_gridVoltage;
    }

    public boolean isHigh_gridVoltage() {
        return isHigh_gridVoltage;
    }

    public void setHigh_gridVoltage(boolean high_gridVoltage) {
        isHigh_gridVoltage = high_gridVoltage;
    }

    public boolean isLow_gridFrequency() {
        return isLow_gridFrequency;
    }

    public void setLow_gridFrequency(boolean low_gridFrequency) {
        isLow_gridFrequency = low_gridFrequency;
    }

    public boolean isHigh_gridFrequency() {
        return isHigh_gridFrequency;
    }

    public void setHigh_gridFrequency(boolean high_gridFrequency) {
        isHigh_gridFrequency = high_gridFrequency;
    }

    public boolean isOutput_overCurrent() {
        return isOutput_overCurrent;
    }

    public void setOutput_overCurrent(boolean output_overCurrent) {
        isOutput_overCurrent = output_overCurrent;
    }

    public boolean isOver_temperatureShutdown() {
        return isOver_temperatureShutdown;
    }

    public void setOver_temperatureShutdown(boolean over_temperatureShutdown) {
        isOver_temperatureShutdown = over_temperatureShutdown;
    }

    public boolean isDCComponentProtection() {
        return isDCComponentProtection;
    }

    public void setDCComponentProtection(boolean DCComponentProtection) {
        isDCComponentProtection = DCComponentProtection;
    }

    public boolean isLeakageCurrentProtection() {
        return isLeakageCurrentProtection;
    }

    public void setLeakageCurrentProtection(boolean leakageCurrentProtection) {
        isLeakageCurrentProtection = leakageCurrentProtection;
    }

    public boolean isLandProtection() {
        return isLandProtection;
    }

    public void setLandProtection(boolean landProtection) {
        isLandProtection = landProtection;
    }

    public boolean isBusOverVoltageProtection() {
        return isBusOverVoltageProtection;
    }

    public void setBusOverVoltageProtection(boolean busOverVoltageProtection) {
        isBusOverVoltageProtection = busOverVoltageProtection;
    }

    public boolean isAbnormal12VPowerSupply() {
        return isAbnormal12VPowerSupply;
    }

    public void setAbnormal12VPowerSupply(boolean abnormal12VPowerSupply) {
        isAbnormal12VPowerSupply = abnormal12VPowerSupply;
    }

    public boolean isResonantShutdown() {
        return isResonantShutdown;
    }

    public void setResonantShutdown(boolean resonantShutdown) {
        isResonantShutdown = resonantShutdown;
    }

    public boolean isControl() {
        return isControl;
    }

    public void setControl(boolean control) {
        isControl = control;
    }

    public boolean isNormal() {
        return isNormal;
    }

    public void setNormal(boolean normal) {
        isNormal = normal;
    }

    public boolean isWait() {
        return isWait;
    }

    public void setWait(boolean wait) {
        isWait = wait;
    }

    public boolean isHitch() {
        return isHitch;
    }

    public void setHitch(boolean hitch) {
        isHitch = hitch;
    }

    public boolean isPV1_overVoltage() {
        return isPV1_overVoltage;
    }

    public void setPV1_overVoltage(boolean PV1_overVoltage) {
        isPV1_overVoltage = PV1_overVoltage;
    }

    public boolean isPV2_overVoltage() {
        return isPV2_overVoltage;
    }

    public void setPV2_overVoltage(boolean PV2_overVoltage) {
        isPV2_overVoltage = PV2_overVoltage;
    }

    public boolean isPV3_overVoltage() {
        return isPV3_overVoltage;
    }

    public void setPV3_overVoltage(boolean PV3_overVoltage) {
        isPV3_overVoltage = PV3_overVoltage;
    }

    public boolean isPV4_overVoltage() {
        return isPV4_overVoltage;
    }

    public void setPV4_overVoltage(boolean PV4_overVoltage) {
        isPV4_overVoltage = PV4_overVoltage;
    }

    public boolean isPV1_underVoltage() {
        return isPV1_underVoltage;
    }

    public void setPV1_underVoltage(boolean PV1_underVoltage) {
        isPV1_underVoltage = PV1_underVoltage;
    }

    public boolean isPV2_underVoltage() {
        return isPV2_underVoltage;
    }

    public void setPV2_underVoltage(boolean PV2_underVoltage) {
        isPV2_underVoltage = PV2_underVoltage;
    }

    public boolean isPV3_underVoltage() {
        return isPV3_underVoltage;
    }

    public void setPV3_underVoltage(boolean PV3_underVoltage) {
        isPV3_underVoltage = PV3_underVoltage;
    }

    public boolean isPV4_underVoltage() {
        return isPV4_underVoltage;
    }

    public void setPV4_underVoltage(boolean PV4_underVoltage) {
        isPV4_underVoltage = PV4_underVoltage;
    }
}
