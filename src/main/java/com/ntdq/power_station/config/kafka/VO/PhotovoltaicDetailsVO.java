package com.ntdq.power_station.config.kafka.VO;

/**
 * 光伏具体情况
 */
public class PhotovoltaicDetailsVO {


    /**
     * 光伏逆变器1状态 正常  故障
     */
    private int PVInverterStatus1;

    /**
     * 光伏逆变器1 转化效率
     */
    private double PVInverterConversionEfficiency1;


    /**
     * 光伏逆变器2状态 正常  故障
     */
    private int PVInverterStatus2;

    /**
     * 光伏逆变器2 转化效率
     */
    private double PVInverterConversionEfficiency2;


    /**
     * 光伏逆变器3状态 正常  故障
     */
    private int PVInverterStatus3;

    /**
     * 光伏逆变器3 转化效率
     */
    private double PVInverterConversionEfficiency3;

    /**
     * 光伏逆变器4状态 正常  故障
     */
    private int PVInverterStatus4;

    /**
     * 光伏逆变器4 转化效率
     */
    private double PVInverterConversionEfficiency4;


    public int getPVInverterStatus1() {
        return PVInverterStatus1;
    }

    public void setPVInverterStatus1(int PVInverterStatus1) {
        this.PVInverterStatus1 = PVInverterStatus1;
    }

    public double getPVInverterConversionEfficiency1() {
        return PVInverterConversionEfficiency1;
    }

    public void setPVInverterConversionEfficiency1(double PVInverterConversionEfficiency1) {
        this.PVInverterConversionEfficiency1 = PVInverterConversionEfficiency1;
    }

    public int getPVInverterStatus2() {
        return PVInverterStatus2;
    }

    public void setPVInverterStatus2(int PVInverterStatus2) {
        this.PVInverterStatus2 = PVInverterStatus2;
    }

    public double getPVInverterConversionEfficiency2() {
        return PVInverterConversionEfficiency2;
    }

    public void setPVInverterConversionEfficiency2(double PVInverterConversionEfficiency2) {
        this.PVInverterConversionEfficiency2 = PVInverterConversionEfficiency2;
    }

    public int getPVInverterStatus3() {
        return PVInverterStatus3;
    }

    public void setPVInverterStatus3(int PVInverterStatus3) {
        this.PVInverterStatus3 = PVInverterStatus3;
    }

    public double getPVInverterConversionEfficiency3() {
        return PVInverterConversionEfficiency3;
    }

    public void setPVInverterConversionEfficiency3(double PVInverterConversionEfficiency3) {
        this.PVInverterConversionEfficiency3 = PVInverterConversionEfficiency3;
    }

    public int getPVInverterStatus4() {
        return PVInverterStatus4;
    }

    public void setPVInverterStatus4(int PVInverterStatus4) {
        this.PVInverterStatus4 = PVInverterStatus4;
    }

    public double getPVInverterConversionEfficiency4() {
        return PVInverterConversionEfficiency4;
    }

    public void setPVInverterConversionEfficiency4(double PVInverterConversionEfficiency4) {
        this.PVInverterConversionEfficiency4 = PVInverterConversionEfficiency4;
    }
}
