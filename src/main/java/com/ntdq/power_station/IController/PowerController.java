package com.ntdq.power_station.IController;

import com.ntdq.power_station.common.rest.RestResponse;
import com.ntdq.power_station.domain.powerStation.PowerStationReport;
import com.ntdq.power_station.util.CommonByteUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.ntdq.power_station.util.ByteUtil.hexStringToBytes;

/**
 * 功率调试
 */
@RestController
@RequestMapping("/power")
public class PowerController {


    /**
     * 判断修改功率是否已经确认!
     */
    private static final AtomicBoolean isModifySuccess = new AtomicBoolean(false);

    /**
     * 功率调节类型
     */
    private static final byte powerAdjustType = 0x61;

    private static final String netUrlPrefix = "192.168";

    @RequestMapping("/modify")
    public synchronized RestResponse modifyPower(double powerStation, String host, int port) throws IOException, InterruptedException {
        if (!host.startsWith(netUrlPrefix)) {
            return RestResponse.createFailed("传入的地址有误!", null);
        }
        String[] remoteAddressArr = host.split("\\.");
        String lastAddr = remoteAddressArr[remoteAddressArr.length - 1];
        String lastAddrHex = Integer.toHexString(Integer.parseInt(lastAddr));
        byte[] AddrByteArrRemote = CommonByteUtils.hexStringToByteArray(lastAddrHex);

        String localAddress = InetAddress.getLocalHost().getHostAddress();
        String[] localAddressArr = localAddress.split("\\.");
        String lastAddrLocal = remoteAddressArr[localAddressArr.length - 1];
        String LastLocalAddrHex = Integer.toHexString(Integer.parseInt(lastAddrLocal));
        byte[] AddrByteArrLocal = CommonByteUtils.hexStringToByteArray(LastLocalAddrHex);
        String format = String.format("%04x", (int)(powerStation*10+1000));
        //修改！
        PowerStationReport.storeAddrPower(lastAddr,powerStation);

        while (!isModifySuccess.get()) {

            byte[] data = new byte[8];
            data[0] = 0x68;
            data[1] = 0x05;
            data[2] = 0x00;
            data[3] = AddrByteArrRemote.length == 1 ? AddrByteArrRemote[0] : 0x00;
            data[4] = AddrByteArrLocal.length == 1 ? AddrByteArrLocal[0] : 0x00;
            data[5] = powerAdjustType;
            data[6] = hexStringToBytes(format.substring(2, 4))[0];
            data[7] = hexStringToBytes(format.substring(0, 2))[0];

            DatagramSocket so = new DatagramSocket(8089);
            java.net.DatagramPacket datagramPacket = new java.net.DatagramPacket(data, data.length, new InetSocketAddress(host, port));
            so.send(datagramPacket);
            so.close();
            Thread.sleep(500);
            isModifySuccess.set(true);
        }
        if (isModifySuccess.get()) {
            //重新复位false 以方便下一次修改
            isModifySuccess.set(false);
            return RestResponse.createSuccess("修改功率成功", null);
        }
        return RestResponse.createSuccess("修改功率失败", null);
    }

    public static boolean resetPowerModifyState() {
        if (!isModifySuccess.get()) {
            isModifySuccess.set(true);
            return true;
        }
        return false;
    }

    @RequestMapping("/test")
    public void test(){
        System.out.println("...");
    }

    public static void main(String[] args) throws InterruptedException, UnknownHostException {
//        while (!isModifySuccess.get()){
//            Thread.sleep(1000);
//            System.out.println("还没完成修改!");
//            new Thread(()->{
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                isModifySuccess.set(true);
//            }).start();
//        }
//        System.out.println("修改完毕");

//        String message = "192.168.1.192";
//        String[] split = message.split("\\.");
//        String s = split[split.length - 1];
//        String sHex = Integer.toHexString(Integer.parseInt(s));
//        byte[] bytes = CommonByteUtils.hexStringToByteArray(sHex);
//        System.out.println(bytes);

        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        System.out.println(hostAddress);
    }
}
