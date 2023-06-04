package com.ntdq.power_station.stationControl.factor;

/**
 * 策略影响因素判断
 * 我最终的目的是能够根据现有的状态知道我现在应该执行那种策略
 * 那每个策略戏份 比如光伏风机策略
 * 它分为四个因素 不同的排列组合便会出发不同的相应策略
 */
public interface InfluencingFactorJudge {

    boolean isNextExecute();


}
