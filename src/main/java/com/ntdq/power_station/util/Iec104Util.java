package com.ntdq.power_station.util;

import com.ntdq.power_station.domain.enumDo.UControlEnum;

/**
 * 
* @ClassName: Iec104Util
* @Description: 工具类 
* @author YDL 
* @date 2020年5月13日
 */
public class Iec104Util {
	
	private static int controlLength = 4;
	
	/**
	 * I 格式 低位在前
	 * @param accept 接收序列号
	 * @param send 发送序列号
	 * @return
	 */
	public static byte[] getIcontrol(short accept, short send) {
		byte[] control = new byte[4];
		// 向左移动一位 保证低位的D0 是0
		send = (short) (send << 1);
		control[0] =  (byte) ((send));
		control[1]  =  (byte) ((send >> 8));
		accept = (short) (accept << 1);
		control[2] =   (byte) ((accept));
		control[3]  =  (byte) ((accept >> 8));
		return control;
	}
	
	/**
	 * 返回控制域中的接收序号
	 * @param control
	 * @return
	 */
	public  static short getAccept(byte[] control) {
		int accept = 0;
		short  acceptLow =   (short) (control[2] & 0xff);
		short  acceptHigh =   (short) (control[3] & 0xff);
		accept += acceptLow;
		accept += acceptHigh << 8;
		accept = accept >> 1;
		return (short) accept;
		
	}
	
	/**
	 * 返回控制域中的发送序号
	 * @param control
	 * @return
	 */
	public  static short getSend(byte[] control) {
		int send = 0;
		short  acceptLow =  (short) (control[0] & 0xff);
		short  acceptHigh =  (short) (control[1] & 0xff);
		send += acceptLow;
		send += acceptHigh << 8;
		send = send >> 1;
		return (short) send;
	}
	
	/**
	 * S 格式
	 * @param accept
	 * @return
	 */
	public static byte[] getScontrol(short accept) {
		byte[] control = new byte[4];
		// 向左移动一位 保证低位的D0 是0
		short send = 1;
		control[0] =  (byte) ((send));
		control[1]  =  (byte) ((send >> 8));
		accept = (short) (accept << 1);
		control[2] =   (byte) ((accept));
		control[3]  =  (byte) ((accept >> 8));
		return control;
	}

	public static void main(String[] args) {
		short a = 5;
		byte[] scontrol = getScontrol(a);
		System.out.println(scontrol);
	}

	/**
	 * 
	* @Title: 返回U帧
	* @Description: 判断是否是
	* @param @param control
	* @param @return 
	* @return boolean   
	* @throws
	 */
	public static UControlEnum getUcontrol(byte[] control) {
		if (control.length < controlLength || control[1] != 0 || control[3] != 0 || control[2] != 0) {
			return null;
		}  
		int controlInt = ByteUtil.byteArrayToInt(control);
		for (UControlEnum ucontrolEnum : UControlEnum.values()) {
			if (ucontrolEnum.getValue() == controlInt) {
				return ucontrolEnum;
			}
		}
		return null;
	}
	
	
	
	
	
	/**
	 * 返回消息地址 其中低位在前
	 * @param i
	 * @return
	 */
	public static byte[] intToMessageAddress(int i) {
        byte[] result = new byte[3];
        result[0] = (byte) (i & 0xFF);
        result[1] = (byte) ((i >> 8) & 0xFF);
        result[2] = (byte) ((i >> 16) & 0xFF);
        return result;
    }
	
	
	
	/**
	 * 消息地址 只有三个
	 * @param bytes
	 * @return
	 */
	public static int messageAddressToInt(byte[] bytes) {
        int value = 0;
        for (int i = 2; i >= 0; i--) {
            int shift = (2 - i) * 8;
            value += (bytes[2 - i] & 0xFF) << shift;
        }
        return value;
    }
	

	/**
	 * short 转换成两个 字节后是163  00    也就是  value[1] 中才有值
	 * test 在D7位置 因此 值应该和  01000000 做与运算
	 * P/N 0肯定确认  1否定确认
	 * @return  肯定或否定确认
	 */
	public static boolean isYes(byte[] values) {
		return (values[0] & 1 << 6) == 0;
	}
	/**
	 *  short 转换成两个 字节后是163  00     也就是  value[1] 中才有值
	 *  test 在D7位置 因此 值应该和 10000000 做与运算
	 *  tets 0 为试验  1 试验
	 * @return 是否试验
	 */
	public static boolean isTets(byte[] values) {
		return (values[0] & 1 << 7) != 0;
	}
	
	/**
	 * 返回具体的原因
	 * @param values
	 * @return
	 */
	public static short getTransferReasonShort(byte[] values) {
		byte transferReason = values[0];
		// 前两位置零
		transferReason = (byte) (transferReason & 0x3E);
		return transferReason;
	}
	
	
	public static short getTransferReasonShort(boolean isTets, boolean isYes, short transferReason) {
		int t = isTets ? 1 : 0;
		int y = isYes ? 0 : 1;
		int transferReasonInt = t << 7 | transferReason;
		transferReasonInt = y << 6 | transferReasonInt;
		
		short transferReasonShort = (short) (transferReasonInt << 8);
		return transferReasonShort;
	}
	
	
	/**
	 *  返回终端地址对应的byte数组 其中低位在前
	 * @param terminalAddress
	 * @return
	 */
	public static byte[] getTerminalAddressByte(short terminalAddress) {
		byte[] b = new byte[2];
		b[1] = (byte) ((terminalAddress >> 8) & 0xff);
		b[0] = (byte) (terminalAddress & 0xff);
		return b;
	}
	
	
	/**
	 *	返回回终端地址 其中低位在前
	 * @param terminalAddress
	 * @return
	 */
	public static short getTerminalAddressShort(byte[] terminalAddress) {
		 short value = 0;
		 value += (terminalAddress[0] & 0xFF);
		 value += (terminalAddress[1] & 0xFF) << 8;
		 return value;
	}
}
