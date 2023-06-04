package com.ntdq.power_station.nettyHandler.parseCore.entyty;

public class PowerPoint {
    private int yx;

    private float yc;


    public int getYx() {
        return yx;
    }

    public void setYx(int yx) {
        this.yx = yx;
    }

    public float getYc() {
        return yc;
    }

    public void setYc(float yc) {
        this.yc = yc;
    }

    @Override
    public String toString() {
        return "PowerPoint{" +
                "yx=" + yx +
                ", yc=" + yc +
                '}';
    }
}
