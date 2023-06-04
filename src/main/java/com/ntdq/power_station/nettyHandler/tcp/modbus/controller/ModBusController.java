package com.ntdq.power_station.nettyHandler.tcp.modbus.controller;

import com.ntdq.power_station.common.rest.RestResponse;
import com.ntdq.power_station.nettyHandler.tcp.modbus.domain.ModBusHeader;
import com.ntdq.power_station.nettyHandler.tcp.modbus.domain.ModBusMessage;
import com.ntdq.power_station.nettyHandler.tcp.modbus.domain.ModBusMessageGenerate;
import com.ntdq.power_station.nettyHandler.tcp.modbus.domain.ModBusPayload;
import com.ntdq.power_station.nettyHandler.tcp.modbus.parm.ModBusParam;
import com.ntdq.power_station.nettyHandler.tcp.modbus.util.ModBusChannelManager;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/modbus")
public class ModBusController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/readCoils", method = RequestMethod.POST)
    public RestResponse<String> readCoils(@RequestBody ModBusParam modBusParam) {
        Channel channel = ModBusChannelManager.getChannel(modBusParam.getIp());
        if (channel != null) {
            ModBusHeader modBusHeader = ModBusMessageGenerate.newReadCoilsReqHeader();
            ModBusPayload modBusPayload = ModBusMessageGenerate.newReadCoilsReqPayLoad(modBusParam.getAddress(), modBusParam.getQuantity());
            ModBusMessage modBusMessage = new ModBusMessage();
            modBusMessage.setModBusHeader(modBusHeader);
            modBusMessage.setModBusPayload(modBusPayload);
            System.out.println(modBusMessage);
            channel.writeAndFlush(modBusMessage);
            return RestResponse.createSuccess("发送命令成功!", "发送命令成功");
        }
        return RestResponse.createFailed("找不到对应的通道!", null);
    }


    @RequestMapping(value = "/readHoldingRegisters", method = RequestMethod.POST)
    public RestResponse<String> readHoldingRegisters(@RequestBody ModBusParam modBusParam) {
        Channel channel = ModBusChannelManager.getChannel(modBusParam.getIp());
        if (channel != null) {
            ModBusHeader modBusHeader = ModBusMessageGenerate.newReadHoldingRegistersReqHeader();
            ModBusPayload modBusPayload = ModBusMessageGenerate.newReadHoldingRegistersReqPayLoad(modBusParam.getAddress(), modBusParam.getQuantity());
            ModBusMessage modBusMessage = new ModBusMessage();
            modBusMessage.setModBusHeader(modBusHeader);
            modBusMessage.setModBusPayload(modBusPayload);
            System.out.println(modBusMessage);
            channel.writeAndFlush(modBusMessage);
            return RestResponse.createSuccess("发送读取保持寄存器成功", null);
        }
        return RestResponse.createError("发送读取保持寄存器失败", null);
    }

    /**
     * 写单个线圈
     *
     * @param modBusParam
     * @return
     */
    @RequestMapping(value = "/writeSingleCoil", method = RequestMethod.POST)
    public RestResponse<String> writeSingleCoil(@RequestBody ModBusParam modBusParam) {
        Channel channel = ModBusChannelManager.getChannel(modBusParam.getIp());
        if (channel != null) {
            ModBusHeader modBusHeader = ModBusMessageGenerate.newWriteSingleCoilReqHeader();
            ModBusPayload modBusPayload = ModBusMessageGenerate.newWriteSingleCoilReqPayLoad(modBusParam.getAddress(), modBusParam.getValue());
            ModBusMessage modBusMessage = new ModBusMessage();
            modBusMessage.setModBusHeader(modBusHeader);
            modBusMessage.setModBusPayload(modBusPayload);
            System.out.println(modBusMessage);
            channel.writeAndFlush(modBusMessage);
            return RestResponse.createSuccess("发送写保持寄存器成功", null);
        }
        return RestResponse.createError("发送写保持寄存器失败", null);
    }

    public static void main(String[] args) {
//        byte[] bytes = intToBytesBig(-1);
//        System.out.println(bytes);

//        byte[] bytes = intToBytesBig(65536);
//        System.out.println(bytes);

        byte[] bytes1 = new byte[2];
        bytes1[0] = -1;
        bytes1[1] = 0;
        int i = littleEndian(bytes1);
        System.out.println(i);

        byte[] bytes = littleEndian(255);
        System.out.println(bytes);
    }

    /**
     * 小端序 字节数组转int
     *
     * @param bytes
     * @return
     */
    public static int littleEndian(byte[] bytes) {
        return (bytes[0] & 0XFF)
                | ((bytes[1] & 0XFF) << 8);
    }

    /**
     * 小端序 int转字节数组
     *
     * @param i
     * @return
     */
    public static byte[] littleEndian(int i) {
        int byte1 = i & 0XFF;
        int byte2 = (i & 0XFF << 8) >> 8;
        return new byte[]{(byte) byte1, (byte) byte2};
    }
    public static byte[] intToBigBytes(int n) {
        int high = ((n & 0xFF00) >> 8);
        int low = (n & 0x00FF);
        byte[] src = new byte[2];
        src[0] = (byte) low;
        src[1] = (byte) high;
        return src;
    }

    public static byte[] intToBytesBig(int n) {
        byte[] src = new byte[2];
        src[0] = (byte) ((n >> 8) & 0xFF);
        src[1] = (byte) (n & 0xFF);
        return src;
    }
}
