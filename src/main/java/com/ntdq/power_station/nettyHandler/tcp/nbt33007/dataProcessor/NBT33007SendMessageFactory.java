package com.ntdq.power_station.nettyHandler.tcp.nbt33007.dataProcessor;


import com.ntdq.power_station.nettyHandler.tcp.nbt33007.constant.Nbt33007FrameTypeConstant;
import com.ntdq.power_station.nettyHandler.tcp.nbt33007.message.MessageDetail;

/**
 * @Description : NBT33007消息工厂
 * @Author : Kang
 * @Date : 2023/4/21 11:12
 * @Version : 1.0
 */
public class NBT33007SendMessageFactory {

    static final byte start = 0x68;

    static final byte[] controlRegion = new byte[]{0x00, 0x00, 0x00, 0x00};

    static final byte[] masterAddress = new byte[]{0x00, 0x00, 0x00, 0x00};

    static final byte[] publicAddress = new byte[]{0x00, 0x00};

    /**
     * 主站心跳确认
     * 主站心跳确认： 68 0C 00 83 00 00 00 F9 A7 FF FF 00 00 00 00
     * 长度    控制域       目标地址     源地址
     */
    public static MessageDetail masterSendHeartbeatConfirm(byte[] targetAddress) {
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setStart(start);
        messageDetail.setEffectiveLength(new byte[]{0x0C, 0x00});
        byte[] heartbeatConfirmControlRegion = new byte[]{(byte) 0x83, 0x00, 0x00, 0x00};
        messageDetail.setControlRegion(heartbeatConfirmControlRegion);
        messageDetail.setTargetAddress(targetAddress);
        messageDetail.setSourceAddress(masterAddress);
        return messageDetail;
    }

    /**
     * 主站向集中器发送心跳
     * 主站向集中器发送心跳： 68 0C 00 43 00 00 00 F9 A7 FF FF 00 00 00 00
     * 长度     控制域       目标地址     源地址
     */
    public static MessageDetail masterSendHeartbeat(byte[] targetAddress) {
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setStart(start);
        messageDetail.setEffectiveLength(new byte[]{0x0C, 0x00});
        byte[] heartbeatConfirmControlRegion = new byte[]{(byte) 0x43, 0x00, 0x00, 0x00};
        messageDetail.setControlRegion(heartbeatConfirmControlRegion);
        messageDetail.setTargetAddress(targetAddress);
        messageDetail.setSourceAddress(masterAddress);
        return messageDetail;
    }

    /**
     * 主站向集中器发送总召唤报文
     * 传送原因 公共地址
     * 68 12 00 00 00 00 00 F9 A7 FF FF 00 00 00 00 03 00 03 00 00 00
     * 长度  控制域          目标地址      源地址      类型 信息体个
     */
    public static MessageDetail masterSendTotalCall(byte[] targetAddress) {
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setStart(start);
        messageDetail.setEffectiveLength(new byte[]{0x12, 0x00});
        messageDetail.setControlRegion(controlRegion);
        messageDetail.setTargetAddress(targetAddress);
        messageDetail.setSourceAddress(masterAddress);
        messageDetail.setFrameType(Nbt33007FrameTypeConstant.TOTAL_CALL);
        messageDetail.setInfoBodyCount((byte) 0x00);
        byte[] sendReason = new byte[]{0x03, 0x00};
        messageDetail.setSendReason(sendReason);
        messageDetail.setPublicAddress(publicAddress);
        return messageDetail;
    }

    /**
     * 充电预约
     * 主站向集中器发送充电预约命令
     * 68 26 00 00 00 00 00 F9 A7 01 00 00 00 00 00 93 01 03 00 00 00
     * 00 00 00 //信息体地址，表示充电口，对于单充电口固定为 0
     * 02 // 预约设置，2 表示预约，1 表示取消预约
     * 33 30 35 30 31 32 30 31 34 30 37 30 31 30 30 31 // 16 位卡号 ASCII 码
     * 注意目标地址，前两个字节表示集中器地址，后两个字节表示充电桩地址，此处示例表示
     * 向地址为 0xA7F9 的集中器下的 1 号充电桩发送预约命令。
     */
    public static MessageDetail masterSendChargingReserve(byte[] targetAddress, byte[] infoBodyAddress, byte[] infoBody) {
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setStart(start);
        messageDetail.setEffectiveLength(new byte[]{0x26, 0x00});
        messageDetail.setControlRegion(controlRegion);
        messageDetail.setTargetAddress(targetAddress);
        messageDetail.setSourceAddress(masterAddress);
        messageDetail.setFrameType(Nbt33007FrameTypeConstant.CHARGING_RESERVE);
        messageDetail.setInfoBodyCount((byte) 0x01);
        byte[] region = new byte[]{0x03, 0x00};
        messageDetail.setSendReason(region);
        messageDetail.setPublicAddress(publicAddress);
        messageDetail.setInfoBodyAddress(infoBodyAddress);
        messageDetail.setInfoBody(infoBody);
        return messageDetail;
    }

    /**
     * 充电启停控制
     * 主站向集中器发送启动/停止充电命令
     * 68 38 00 00 00 00 00 F9 A7 01 00 00 00 00 00 41 01 03 00 00 00
     * 00 00 00 //信息体地址，固定为 0
     * 00 //充电口
     * 01 //遥控性质，1 表示启动充电，0 表示停止充电
     * 01 //控制信息，对于交流充电桩可不处理
     * 1D 0A 0C 02 //定时时间，对于交流充电桩可不处理
     * 00 00 00 00 //最高允许电压，对于交流充电桩可不处理
     * 00 00 00 00 //最高允许电流，对于交流充电桩可不处理
     * 00 00 20 41 //充电控制数据，对于交流充电桩可不处理
     * 33 30 35 30 31 32 30 31 34 30 37 30 31 30 30 31 // 16 位卡号 ASCII 码
     */
    public static MessageDetail masterSendStartStopChargingCommand(byte[] targetAddress, byte[] infoBody) {
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setStart(start);
        messageDetail.setEffectiveLength(new byte[]{0x38, 0x00});
        messageDetail.setControlRegion(controlRegion);
        messageDetail.setTargetAddress(targetAddress);
        messageDetail.setSourceAddress(masterAddress);
        messageDetail.setFrameType(Nbt33007FrameTypeConstant.START_STOP_CONTROL);
        messageDetail.setInfoBodyCount((byte) 0x01);
        byte[] region = new byte[]{0x03, 0x00};
        messageDetail.setSendReason(region);
        messageDetail.setPublicAddress(publicAddress);
        byte[] infoBodyAddress = new byte[]{0x00, 0x00, 0x00};
        messageDetail.setInfoBodyAddress(infoBodyAddress);//信息体地址，固定为 0
        messageDetail.setInfoBody(infoBody);
        return messageDetail;
    }

    /**
     * 上送充电记录
     * 主站收到充电记录后进行确认（将传送原因改为 04）。
     * 集中器只有收到主站的确认后才认为本次充电记录发送成功，若没有收到确认则需要重复发送充电记录：
     * 68 70 00 00 00 00 00 F9 A7 01 00 00 00 00 00 42 01 04 00 00 00
     * 00 00 00 //信息体地址，表示交易的支付方式：0—未支付扣值；1—已从卡内扣值支付
     * 1C 1D 0A 0C 02 0F //充电开始时间，秒 分 时 日 月 年-2000
     * 21 1E 0A 0C 02 0F //充电结束时间，秒 分 时 日 月 年-2000
     * 33 30 35 30 31 32 30 31 34 30 37 30 31 30 30 31 // 16 位卡号 ASCII 码
     * 00 00 00 00 //充电前电表读数，FLOAT
     * 00 00 00 00 //充电后电表读数，FLOAT
     * 39 47 7F 3D //本次充电总电量，FLOAT
     * 39 47 7F 3D //本次充电金额，FLOAT
     * 00 00 00 00 //充电前卡余额，FLOAT
     * 00 00 00 00 //充电后卡余额，FLOAT
     * 00 00 00 00 //本次充电峰电量，FLOAT
     * 00 00 00 00 //本次充电谷电量，FLOAT
     * 00 00 00 00 //本次充电尖电量，FLOAT
     * 00 00 00 00 //本次充电平电量，FLOAT
     * XX XX XX XX //本次充电峰时段费用，FLOAT
     * XX XX XX XX //本次充电谷时段费用，FLOAT
     * XX XX XX XX //本次充电尖时段费用，FLOAT
     * XX XX XX XX //本次充电平时段费用，FLOAT
     * XX //中止 SOC
     * XX XX //充电中止原因
     * XX XX XX XX //充电中止原因辅助信
     */
    public static MessageDetail chargingRecordConfirm(byte[] targetAddress, byte[] infoBody) {
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setStart(start);
        messageDetail.setEffectiveLength(new byte[]{0x70, 0x00});
        messageDetail.setControlRegion(controlRegion);
        messageDetail.setTargetAddress(targetAddress);
        messageDetail.setSourceAddress(masterAddress);
        messageDetail.setFrameType(Nbt33007FrameTypeConstant.CHARGING_RECORD);
        messageDetail.setInfoBodyCount((byte) 0x01); //信息体个数，1
        byte[] region = new byte[]{0x04, 0x00};
        messageDetail.setSendReason(region);
        messageDetail.setPublicAddress(publicAddress);
        byte[] infoBodyAddress = new byte[]{0x00, 0x00, 0x00};
        messageDetail.setInfoBodyAddress(infoBodyAddress);  //信息体地址，固定为 0
        messageDetail.setInfoBody(infoBody);
        return messageDetail;
    }

    /**
     * 刷卡充电请求
     * 主站接收到刷卡启动充电请求后进行校验，若校验通过，则直接下发启动充电命令（5.5），
     * 待接收到充电桩响应后再返回刷卡请求处理结果（可能成功或失败，取决于充电桩的响应
     * 结果）；若校验不通过，则直接返回刷卡请求处理结果（失败）。
     * 主站返回刷卡请求处理结果：
     * 68 23 00 00 00 00 00 F9 A7 01 00 00 00 00 00 94 01 03 00 00 00
     * 00 00 00 //信息体地址，表示充电口编号，单充固定为 0
     * 01 //请求处理结果，1 成功，0 失败
     * 0C//结果字符长度，最大 255 字节
     * C6 F4 B6 AF B3 E4 B5 E7 B3 C9 B9 A6 //处理结果文字提示信息， ASCII 码
     */
    public static MessageDetail masterReturnBrushCardRequestResult(byte[] targetAddress, byte[] publicAddr, byte[] infoBody) {
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setStart(start);
        messageDetail.setEffectiveLength(new byte[]{0x23, 0x00});
        messageDetail.setControlRegion(controlRegion);
        messageDetail.setTargetAddress(targetAddress);
        messageDetail.setSourceAddress(masterAddress);
        messageDetail.setFrameType(Nbt33007FrameTypeConstant.CHARGING_REQUEST);
        messageDetail.setInfoBodyCount((byte) 0x01);
        byte[] region = new byte[]{0x03, 0x00}; //传送原因，03—激活
        messageDetail.setSendReason(region);
        messageDetail.setPublicAddress(publicAddr); //公共地址，扩展为表示卡类型：0—平台发行卡;1—国网充电卡;2—市民卡;...留待扩展
        byte[] infoBodyAddress = new byte[]{0x00, 0x00, 0x00};//单充固定为 0
        messageDetail.setInfoBodyAddress(infoBodyAddress);
        messageDetail.setInfoBody(infoBody);
        return messageDetail;
    }

    /**
     * 卡扣值
     * 主站下发扣值命令：
     * 68 29 00 00 00 00 00 F9 A7 01 00 00 00 00 00 95 01 03 00 00 00
     * 00 00 00 //信息体地址，固定为 0
     * 33 30 35 30 31 32 30 31 34 30 37 30 31 30 30 31 // 16 位卡号 ASCII 码
     * 39 47 7F 3D //扣值金额，FLOAT
     */
    public static MessageDetail masterSendDeductionValue(byte[] targetAddress, byte[] infoBody) {
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setStart(start);
        messageDetail.setEffectiveLength(new byte[]{0x29, 0x00});
        messageDetail.setControlRegion(controlRegion);
        messageDetail.setTargetAddress(targetAddress);
        messageDetail.setSourceAddress(masterAddress);
        messageDetail.setFrameType(Nbt33007FrameTypeConstant.MASTER_DEDUCTION_VALUE);
        messageDetail.setInfoBodyCount((byte) 0x01);
        byte[] reason = new byte[]{0x03, 0x00};
        messageDetail.setSendReason(reason);    //传送原因：03-激活
        messageDetail.setPublicAddress(publicAddress); //公共地址，扩展为表示卡类型：0—平台发行卡;1—国网充电卡;2—市民卡;...留待扩展
        byte[] infoBodyAddress = new byte[]{0x00, 0x00, 0x00};//信息体地址，固定为 0
        messageDetail.setInfoBodyAddress(infoBodyAddress);
        messageDetail.setInfoBody(infoBody);
        return messageDetail;
    }

    /**
     *
     */

    /**
     * 对时
     * 主站下发对时命令：
     * 68 1C 00 00 00 00 00 F9 A7 FF FF 00 00 00 00 02 01 03 00 00 00
     * 00 00 00 //信息体地址，固定为 0
     * 20 CB // 毫秒，52000
     * 08 // 分，8
     * 10 // 时，16
     * 0A // 日，10
     * 05 // 月，5
     * 0F // 年，2015
     * 2015-5-10 16:08:52
     */
    public static MessageDetail onTime(byte[] targetAddress, byte[] infoBody) {
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setStart(start);
        messageDetail.setEffectiveLength(new byte[]{0x1C, 0x00});
        messageDetail.setControlRegion(controlRegion);
        messageDetail.setTargetAddress(targetAddress);
        messageDetail.setSourceAddress(masterAddress);
        messageDetail.setFrameType(Nbt33007FrameTypeConstant.ON_TIME);
        messageDetail.setInfoBodyCount((byte) 0x01);
        byte[] region = new byte[]{0x03, 0x00};
        messageDetail.setSendReason(region);
        messageDetail.setPublicAddress(publicAddress);
        byte[] infoBodyAddress = new byte[]{0x00, 0x00, 0x00};//信息体地址，固定为 0
        messageDetail.setInfoBodyAddress(infoBodyAddress);
        messageDetail.setInfoBody(infoBody);
        return messageDetail;
    }

    /**
     * 查询卡信息
     * 充电桩查询卡信息：
     * 68 XX XX 00 00 00 00 00 00 00 00 F9 A7 FF FF 13 01 03 00 00 00
     * 00 00 00 //信息体地址，固定为 0
     * 33 30 35 30 31 32 30 31 34 30 37 30 31 30 30 31 // 16 位卡号 ASCII 码
     * 06 //密码长度
     * 31 32 33 34 35 36 // 密码，ASCII 码
     * 主站返回卡信息：
     * 68 XX XX 00 00 00 00 F9 A7 FF FF 00 00 00 00 13 01
     * 00 00 00 //信息体地址，固定为 0
     * 04 00 //传送原因，04—确认
     * 00 00 //公共地址
     * 33 30 35 30 31 32 30 31 34 30 37 30 31 30 30 31 // 16 位卡号 ASCII 码
     * 02 // 卡状态 ==> 0-卡不存在1-卡未激活;2-正常;3-黑名单;4-已挂失;5-已注销
     * XX XX XX XX //卡余额，FLOAT   IEEE 754 短实数（R32.23）
     */
    public static MessageDetail masterReturnsCardInfo(byte[] effectiveLength, byte[] targetAddress, byte[] infoBody) {
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setStart(start);
        messageDetail.setEffectiveLength(effectiveLength);
        messageDetail.setControlRegion(controlRegion);
        messageDetail.setTargetAddress(targetAddress);
        messageDetail.setSourceAddress(masterAddress);
        messageDetail.setFrameType(Nbt33007FrameTypeConstant.CARD_INFO_QUERY);
        messageDetail.setInfoBodyCount((byte) 0x01); //信息体个数，固定为 1
        byte[] region = new byte[]{0x04, 0x00}; //传送原因，04—激活确认
        messageDetail.setSendReason(region);
        messageDetail.setPublicAddress(publicAddress);
        byte[] infoBodyAddress = new byte[]{0x00, 0x00, 0x00};//信息体地址，固定为 0
        messageDetail.setInfoBodyAddress(infoBodyAddress);
        messageDetail.setInfoBody(infoBody);
        return messageDetail;
    }

    /**
     * 充放电功率调控
     * 主站下发充放电功率调控指令
     */
    public static MessageDetail masterSendChargeDischargePowerRegulationCommand(byte[] effectiveLength, byte[] targetAddress, byte[] infoBodyAddress, byte[] infoBody) {
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setStart(start);
        messageDetail.setEffectiveLength(effectiveLength);
        messageDetail.setControlRegion(controlRegion);
        messageDetail.setTargetAddress(targetAddress);
        messageDetail.setSourceAddress(masterAddress);
        messageDetail.setFrameType(Nbt33007FrameTypeConstant.POWER_REGULATION);
        messageDetail.setInfoBodyCount((byte) 0x01); //信息体个数，固定为 1
        byte[] region = new byte[]{0x03, 0x00}; //传送原因，03--激活
        messageDetail.setSendReason(region);
        messageDetail.setPublicAddress(publicAddress);
        messageDetail.setInfoBodyAddress(infoBodyAddress); //信息体地址，调控类型：0：功率值; 1：PWM百分比; 其他留待扩展
        messageDetail.setInfoBody(infoBody); //调控功率或 PWM 百分比值，两位小数。例如控制功率为30.5kW，则下发30.5*100=3050；如控制PWM百分比为50%，则下发0.5*100=50。
        return messageDetail;
    }

    /**
     * 上送车辆 VIN 请求启动充电
     * 充电插头连接就绪后，充电机上送 VIN 请求启动充电
     * 主站校验 VIN，如果检验通过，则直接下发 5.5 的充电启动控制命令；如果检验不通过，则回复如下报文：
     */
    public static MessageDetail masterCheckVINFail(byte[] targetAddress) {
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setStart(start);
        messageDetail.setEffectiveLength(new byte[]{0x16, 0x00});
        messageDetail.setControlRegion(controlRegion);
        messageDetail.setTargetAddress(targetAddress);
        messageDetail.setSourceAddress(masterAddress);
        messageDetail.setFrameType(Nbt33007FrameTypeConstant.CHARGING_VEHICLE_VIN);
        messageDetail.setInfoBodyCount((byte) 0x01); //信息体个数，固定为 1
        byte[] region = new byte[]{0x03, 0x00}; //传送原因，03--激活
        messageDetail.setSendReason(region);
        messageDetail.setPublicAddress(publicAddress);
        byte[] infoBodyAddress = new byte[]{0x00, 0x00, 0x00};//信息体地址，固定为 0
        messageDetail.setInfoBodyAddress(infoBodyAddress); //信息体地址，固定为 0
        byte[] infoBody = new byte[]{0x00};
        messageDetail.setInfoBody(infoBody); //00，表示 VIN 校验失败，无法启动充电
        return messageDetail;
    }

    /**
     * 计费模型下发
     * 主站向充电桩回复计费模型
     */
    public static MessageDetail masterResponseBillingModel(byte[] effectiveLength, byte[] targetAddress, byte[] infoBody) {
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setStart(start);
        messageDetail.setEffectiveLength(effectiveLength);
        messageDetail.setControlRegion(controlRegion);
        messageDetail.setTargetAddress(targetAddress);
        messageDetail.setSourceAddress(masterAddress);
        messageDetail.setFrameType(Nbt33007FrameTypeConstant.BILLING_MODEL);
        messageDetail.setInfoBodyCount((byte) 0x01); //信息体个数，固定为 1
        byte[] region = new byte[]{0x04, 0x00}; //传送原因，04--激活确认
        messageDetail.setSendReason(region);
        messageDetail.setPublicAddress(publicAddress);
        byte[] infoBodyAddress = new byte[]{0x00, 0x00, 0x00};//信息体地址，固定为 0
        messageDetail.setInfoBodyAddress(infoBodyAddress); //信息体地址，固定为 0
        messageDetail.setInfoBody(infoBody);
        return messageDetail;
    }

    /**
     * 遥控功能
     * 主站下发遥控命令
     * 信息体共2字节：
     * XXH 1字节遥控功能码：1：重启 TCU；2：投入/退出检修
     * XXH 合/分：0：退出；1：投入
     */
    public static MessageDetail masterSendRemoteCommand(byte[] targetAddress, byte[] infoBody) {
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setStart(start);
        messageDetail.setEffectiveLength(new byte[]{0x17, 0x00});
        messageDetail.setControlRegion(controlRegion);
        messageDetail.setTargetAddress(targetAddress);
        messageDetail.setSourceAddress(masterAddress);
        messageDetail.setFrameType(Nbt33007FrameTypeConstant.YK);
        messageDetail.setInfoBodyCount((byte) 0x01); //信息体个数，固定为 1
        byte[] region = new byte[]{0x03, 0x00}; //传送原因，03--激活
        messageDetail.setSendReason(region);
        messageDetail.setPublicAddress(publicAddress);
        byte[] infoBodyAddress = new byte[]{0x00, 0x00, 0x00};//信息体地址，固定为 0
        messageDetail.setInfoBodyAddress(infoBodyAddress); //信息体地址，固定为 0
        messageDetail.setInfoBody(infoBody);
        return messageDetail;
    }

    /**
     * 召唤充电桩档案
     * 主站向下发请求
     * 信息体：
     * 00H
     * 00H
     * 版本号
     * ASCII
     * 16字节的 MD5 校验码
     * 00H
     * 00H
     * 两个字节的文件网址字符串长度
     * ASCII 文件网址字符串，不允许包含中文，ASCII形式，如
     * ftp://xxx@xxx:xxx.com:81/path/a.bin
     * http://www.xxx.com:81/path/a.bin
     */
    public static MessageDetail masterSendChargingPileArchivesRequest(byte[] targetAddress) {
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setStart(start);
        messageDetail.setEffectiveLength(new byte[]{0x15, 0x00});
        messageDetail.setControlRegion(controlRegion);
        messageDetail.setTargetAddress(targetAddress);
        messageDetail.setSourceAddress(masterAddress);
        messageDetail.setFrameType(Nbt33007FrameTypeConstant.CHARGING_PILE_ARCHIVES_INFO);
        messageDetail.setInfoBodyCount((byte) 0x01); //信息体个数，固定为 1
        byte[] region = new byte[]{0x03, 0x00}; //传送原因，03--激活
        messageDetail.setSendReason(region);
        messageDetail.setPublicAddress(publicAddress);
        byte[] infoBodyAddress = new byte[]{0x00, 0x00, 0x00};//信息体地址，固定为 0
        messageDetail.setInfoBodyAddress(infoBodyAddress); //信息体地址，固定为 0
        return messageDetail;
    }

    /**
     * 下发充电桩固件程序
     * 主站请求
     */
    public static MessageDetail masterRequestFirmwareVersion(byte[] effectiveLength, byte[] targetAddress, byte[] infoBody) {
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setStart(start);
        messageDetail.setEffectiveLength(effectiveLength);
        messageDetail.setControlRegion(controlRegion);
        messageDetail.setTargetAddress(targetAddress);
        messageDetail.setSourceAddress(masterAddress);
        messageDetail.setFrameType(Nbt33007FrameTypeConstant.FIRMWARE_VERSION);
        messageDetail.setInfoBodyCount((byte) 0x01); //信息体个数，固定为 1
        byte[] region = new byte[]{0x03, 0x00}; //传送原因，03--激活
        messageDetail.setSendReason(region);
        messageDetail.setPublicAddress(publicAddress);
        byte[] infoBodyAddress = new byte[]{0x00, 0x00, 0x00};
        messageDetail.setInfoBodyAddress(infoBodyAddress); //信息体地址，一般为 0   如果一次下装多个固件，则信息体个数和信息体地址会递增
        messageDetail.setInfoBody(infoBody);
        return messageDetail;
    }

    /**
     * 下发有序充电功率曲线
     * 曲线点 1 偏移时间应为 0，偏移时间点应按升序排列
     * 一个调控曲线最多 300 个时间点
     * 收到命令时，已经超过命令开始时间的，充电桩应选择不晚于下一个时间点的上一个时间点开始执行
     * 充电桩正在执行有序充电命令时，如收到新的有序充电命令，应停止当前命令，执行新的命令
     * 主站请求
     * 信息体：
     * 03H
     * 00H
     * 曲线点个数
     * 00 10 08 09 03 0D 执行开始时间
     * 秒 分 时 日 月 年-2000
     * 2013-3-9 8:16:00
     * 00H
     * 00H
     * 曲线点 1 偏移时间，单位分
     * 00H
     * 00H
     * 曲线点 1 功率，分辨率 0.1kW
     * 3CH
     * 00H
     * 曲线点 2 偏移时间，单位分
     * 00H
     * 00H
     * 曲线点 2 功率，分辨率 0.1kW
     * 78H
     * 00H
     * 曲线点 3 偏移时间，单位分
     * 00H
     * 00H
     * 曲线点 3 功率，分辨率 0.1kW
     * ...... ......
     */
    public static MessageDetail masterSendOrderlyChargingCurve(byte[] effectiveLength, byte[] targetAddress, byte[] infoBody) {
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setStart(start);
        messageDetail.setEffectiveLength(effectiveLength);
        messageDetail.setControlRegion(controlRegion);
        messageDetail.setTargetAddress(targetAddress);
        messageDetail.setSourceAddress(masterAddress);
        messageDetail.setFrameType(Nbt33007FrameTypeConstant.ORDERLY_CHARGING_CURVE);
        messageDetail.setInfoBodyCount((byte) 0x01); //信息体个数，固定为 1
        byte[] region = new byte[]{0x03, 0x00}; //传送原因，03--激活
        messageDetail.setSendReason(region);
        messageDetail.setPublicAddress(publicAddress);
        byte[] infoBodyAddress = new byte[]{0x00, 0x00, 0x00};
        messageDetail.setInfoBodyAddress(infoBodyAddress); //信息体地址，为 0
        messageDetail.setInfoBody(infoBody);
        return messageDetail;
    }
}
