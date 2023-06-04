package com.ntdq.power_station.remoteControl;

@FunctionalInterface
public interface ControlCommandCallback {

    void onCommandResult(boolean success);

}
