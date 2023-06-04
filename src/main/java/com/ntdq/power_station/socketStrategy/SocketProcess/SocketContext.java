package com.ntdq.power_station.socketStrategy.SocketProcess;

import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;

public class SocketContext {

    private final ServerProcess context;


    public SocketContext(ServerProcess serverProcess) {
        this.context = serverProcess;
    }


    public void startNettyServer(ServerBaseConfiguration configuration) {
        this.context.startServer(configuration);
    }

    public void stopNettyServer() {
        this.context.stopServer();
    }


}
