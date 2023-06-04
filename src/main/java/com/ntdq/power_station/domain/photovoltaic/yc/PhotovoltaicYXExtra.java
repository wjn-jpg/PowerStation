package com.ntdq.power_station.domain.photovoltaic.yc;

public class PhotovoltaicYXExtra {

    private float Uab;

    private float Ubc;

    private float Uca;

    private float Ua;

    private float Ub;

    private float Uc;

    private float Ia;

    private float Ib;

    private float Ic;

    private float P;

    private float Q;

    private float COS;

    private float F;

    /**
     * 有功功率
     */
    private float active_power;

    /**
     * 无功功率
     */
    private float reactive_power;

    /**
     * 功率因数
     */
    private float power_factor;

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
     * A相电流
     */
    private float A_current;

    /**
     * B相电流
     */
    private float B_current;

    /**
     * C相电流
     */
    private float C_current;

    public float getUab() {
        return Uab;
    }

    public void setUab(float uab) {
        Uab = uab;
    }

    public float getUbc() {
        return Ubc;
    }

    public void setUbc(float ubc) {
        Ubc = ubc;
    }

    public float getUca() {
        return Uca;
    }

    public void setUca(float uca) {
        Uca = uca;
    }

    public float getUa() {
        return Ua;
    }

    public void setUa(float ua) {
        Ua = ua;
    }

    public float getUb() {
        return Ub;
    }

    public void setUb(float ub) {
        Ub = ub;
    }

    public float getUc() {
        return Uc;
    }

    public void setUc(float uc) {
        Uc = uc;
    }

    public float getIa() {
        return Ia;
    }

    public void setIa(float ia) {
        Ia = ia;
    }

    public float getIb() {
        return Ib;
    }

    public void setIb(float ib) {
        Ib = ib;
    }

    public float getIc() {
        return Ic;
    }

    public void setIc(float ic) {
        Ic = ic;
    }

    public float getP() {
        return P;
    }

    public void setP(float p) {
        P = p;
    }

    public float getQ() {
        return Q;
    }

    public void setQ(float q) {
        Q = q;
    }

    public float getCOS() {
        return COS;
    }

    public void setCOS(float COS) {
        this.COS = COS;
    }

    public float getF() {
        return F;
    }

    public void setF(float f) {
        F = f;
    }

    public float getActive_power() {
        return active_power;
    }

    public void setActive_power(float active_power) {
        this.active_power = active_power;
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

    public float getA_current() {
        return A_current;
    }

    public void setA_current(float a_current) {
        A_current = a_current;
    }

    public float getB_current() {
        return B_current;
    }

    public void setB_current(float b_current) {
        B_current = b_current;
    }

    public float getC_current() {
        return C_current;
    }

    public void setC_current(float c_current) {
        C_current = c_current;
    }
}
