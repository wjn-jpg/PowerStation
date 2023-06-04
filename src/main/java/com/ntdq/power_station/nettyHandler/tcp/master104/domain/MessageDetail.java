package com.ntdq.power_station.nettyHandler.tcp.master104.domain;

import com.ntdq.power_station.nettyHandler.tcp.master104.constant.BasicInstruction104;
import com.ntdq.power_station.nettyHandler.tcp.master104.constant.Iec104Constant;
import com.ntdq.power_station.util.ByteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 发送消息实体类
 */
public class MessageDetail {

    private final static Logger logger = LoggerFactory.getLogger(MessageDetail.class);

    /**
     * 0 s确认帧
     * 1 遥控遥信
     */
    private final int messageType;

    private int accept;

    private int send;

    private byte[] sendData;

    /**
     * 处理I帧的构造器
     *
     * @param messageType
     */
    public MessageDetail(int messageType) {
        this.messageType = messageType;
        if (messageType == Iec104Constant.SEND_NUMBER_ALL) {
            this.sendData = BasicInstruction104.ALL;
        } else if (messageType == Iec104Constant.SEND_NUMBER_YK) {

        }
    }

    /**
     * 专门用来处理S帧的构造器
     *
     * @param messageType
     * @param number      如果是S确认帧 代表accept接收序列号 如何messageType是yk 代表地址
     */
    public MessageDetail(int messageType, short number) {
        this.messageType = messageType;
        if (messageType == Iec104Constant.S) {
            int i = number + 1;
            int data = i * 2;
            String s = String.format("%04x", data);
            String high = s.substring(0, 2);
            String low = s.substring(2);
            byte highByte = hexStringToByteArray(high)[0];
            byte lowByte = hexStringToByteArray(low)[0];
            sendData = new byte[]{0x68, 0x04, 0x01, 0x00, lowByte, highByte};
        } else if (messageType == Iec104Constant.SEND_NUMBER_YK) {
            logger.info("不是s帧 是遥控命令!");
        }
    }

    public byte[] getSendData() {
        return sendData;
    }

    public void setSend(int send) {
        //S帧没必要知道发送序列号 只需要知道接受序列号
        if (messageType != Iec104Constant.S) {
            this.send = send;
            if (messageType == Iec104Constant.SEND_NUMBER_ALL) {
                int i = send + 1;
                int data = i * 2;
                String s = String.format("%04x", data);
                String high = s.substring(0, 2);
                String low = s.substring(2);
                byte highByte = Byte.parseByte(high);
                byte lowByte = Byte.parseByte(low);
                sendData[2] = highByte;
                sendData[3] = lowByte;
            }
        }
    }

    public int getMessageType() {
        return messageType;
    }

    public int getAccept() {
        return accept;
    }

    public void setAccept(int accept) {
        this.accept = accept;
    }

    public int getSend() {
        return send;
    }

    private MessageYK messageYK;

    public MessageYK getMessageYK() {
        return messageYK;
    }

    public class MessageYK {

        private String address;

        /**
         * 可变限定词
         */
        private String value;

        public MessageDetail setAddress(String address) {
            this.address = address;
            messageYK = this;
            return MessageDetail.this;
        }

        public MessageYK setValue(String value) {
            this.value = value;
            messageYK = this;
            return this;
        }

        public MessageDetail build() {
            byte[] bytes = null;
            byte[] byteAccept = null;
            byte[] byteSend = null;
            byte[] addressByteArr = null;
            switch (messageType) {
                case 2: //单点遥控45
                    bytes = new byte[16];
                    bytes[0] = 0x68;
                    bytes[1] = 0x0E;
                    byteAccept = getByteArr(send);
                    bytes[2] = byteAccept[0];
                    bytes[3] = byteAccept[1];
                    byteSend = getByteArr(accept);
                    bytes[4] = byteSend[0];
                    bytes[5] = byteSend[1];
                    bytes[6] = 0x2D;
                    bytes[7] = 0x01;
                    bytes[8] = 0x06;
                    bytes[9] = 0x00;
                    bytes[10] = 0x01;
                    bytes[11] = 0x00;
                    addressByteArr = ByteUtil.hexStringToBytes(address);
                    bytes[12] = addressByteArr[0];
                    bytes[13] = addressByteArr[1];
                    bytes[14] = addressByteArr[2];
                    bytes[15] = 0x01; //遥控执行报文发送
                    break;
                case 3://遥调50
                    bytes = new byte[20];
                    bytes[0] = 0x68;
                    bytes[1] = 0x12;
                    byteAccept = getByteArr(send);
                    bytes[2] = byteAccept[0];
                    bytes[3] = byteAccept[1];
                    byteSend = getByteArr(accept);
                    bytes[4] = byteSend[0];
                    bytes[5] = byteSend[1];
                    bytes[6] = 0x32;
                    bytes[7] = 0x01;
                    bytes[8] = 0x06;
                    bytes[9] = 0x00;
                    bytes[10] = 0x01;
                    bytes[11] = 0x00;
                    addressByteArr = ByteUtil.hexStringToBytes(address);
                    bytes[12] = addressByteArr[0];
                    bytes[13] = addressByteArr[1];
                    bytes[14] = addressByteArr[2];
                    byte[] valueArr = ByteUtil.hexStringToBytes(value);
                    bytes[15] = valueArr[0];
                    bytes[16] = valueArr[1];
                    bytes[17] = valueArr[2];
                    bytes[18] = valueArr[3];
                    bytes[19] = 0x00; //遥调 单步执行 浮点数
            }
            MessageDetail.this.sendData = bytes;
            return MessageDetail.this;
        }
    }

    private static byte[] getByteArr(int data) {
        byte[] bytes = new byte[2];
        String sendStr = String.format("%04x", data);
        String high = sendStr.substring(0, 2);
        String low = sendStr.substring(2);
        byte highByte = Byte.parseByte(high);
        byte lowByte = Byte.parseByte(low);
        bytes[0] = lowByte;
        bytes[1] = highByte;
        return bytes;
    }

    public static byte[] hexStringToByteArray(String hexString) {
        hexString = hexString.replaceAll(" ", "");
        int len = hexString.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character
                    .digit(hexString.charAt(i + 1), 16));
        }
        return bytes;
    }

    public static void main(String[] args) {
        String message = "60";
        System.out.println(Byte.parseByte(message, 16));
    }

}
