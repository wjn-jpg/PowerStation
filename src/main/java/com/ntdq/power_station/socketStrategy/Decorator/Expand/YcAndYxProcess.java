package com.ntdq.power_station.socketStrategy.Decorator.Expand;

@FunctionalInterface
public interface YcAndYxProcess {

    void processYXOrYC(Integer address, String value);

}
