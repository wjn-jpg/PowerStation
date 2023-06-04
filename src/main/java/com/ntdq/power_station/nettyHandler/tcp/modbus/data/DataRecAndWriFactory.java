package com.ntdq.power_station.nettyHandler.tcp.modbus.data;

import com.ntdq.power_station.nettyHandler.tcp.modbus.constant.ModBusFunctionCode;
import com.ntdq.power_station.nettyHandler.tcp.modbus.data.res.ReadCoilsResMessageFactory;
import com.ntdq.power_station.nettyHandler.tcp.modbus.data.res.ReadHoldingRegistersFactory;

public class DataRecAndWriFactory {

    public static RecAndWriMessage getMessageFactory(int functionCode) {
        RecAndWriMessage recAndWriMessage = null;
        switch (functionCode) {
            case ModBusFunctionCode.ReadCoils:
                recAndWriMessage = new ReadCoilsResMessageFactory();
                break;
            case ModBusFunctionCode.ReadHoldingRegisters:
                recAndWriMessage = new ReadHoldingRegistersFactory();
                break;
        }
        return recAndWriMessage;
    }


}
