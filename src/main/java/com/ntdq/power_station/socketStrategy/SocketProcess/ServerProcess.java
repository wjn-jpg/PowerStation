package com.ntdq.power_station.socketStrategy.SocketProcess;

import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;

public interface ServerProcess{


    void startServer(ServerBaseConfiguration appConfiguration);


    void stopServer();



}
