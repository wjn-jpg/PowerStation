package com.ntdq.power_station.domain.photovoltaic.yc;

public class Photovoltaic {

    /**
     * 逆变器1
     */
    private PhotovoltaicInverter photovoltaicInverter1;

    /**
     * 逆变器2
     */
    private PhotovoltaicInverter photovoltaicInverter2;

    /**
     * 逆变器3
     */
    private PhotovoltaicInverter photovoltaicInverter3;

    /**
     * 逆变器4
     */
    private PhotovoltaicInverter photovoltaicInverter4;

    /**
     * 额外总体信息
     */
    private PhotovoltaicYXExtra photovoltaicExtra;

    public PhotovoltaicInverter getPhotovoltaicInverter1() {
        return photovoltaicInverter1;
    }

    public void setPhotovoltaicInverter1(PhotovoltaicInverter photovoltaicInverter1) {
        this.photovoltaicInverter1 = photovoltaicInverter1;
    }

    public PhotovoltaicInverter getPhotovoltaicInverter2() {
        return photovoltaicInverter2;
    }

    public void setPhotovoltaicInverter2(PhotovoltaicInverter photovoltaicInverter2) {
        this.photovoltaicInverter2 = photovoltaicInverter2;
    }

    public PhotovoltaicInverter getPhotovoltaicInverter3() {
        return photovoltaicInverter3;
    }

    public void setPhotovoltaicInverter3(PhotovoltaicInverter photovoltaicInverter3) {
        this.photovoltaicInverter3 = photovoltaicInverter3;
    }

    public PhotovoltaicInverter getPhotovoltaicInverter4() {
        return photovoltaicInverter4;
    }

    public void setPhotovoltaicInverter4(PhotovoltaicInverter photovoltaicInverter4) {
        this.photovoltaicInverter4 = photovoltaicInverter4;
    }

    public PhotovoltaicYXExtra getPhotovoltaicExtra() {
        return photovoltaicExtra;
    }

    public void setPhotovoltaicExtra(PhotovoltaicYXExtra photovoltaicExtra) {
        this.photovoltaicExtra = photovoltaicExtra;
    }
}
