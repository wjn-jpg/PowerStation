package com.ntdq.power_station.domain.photovoltaic.yx;

import com.ntdq.power_station.common.annotation.ModelArguments;
import com.ntdq.power_station.common.annotation.ModelDeviceInfo;

/**
 * 南瑞测控装置
 */
@ModelDeviceInfo(mainType = 2, modelType = 2, subType = 4)
public class NariMeasureControlDeviceYX {


    /**
     * 闭锁重合闸
     */
    @ModelArguments(code = "BlockingRec", mean = "闭锁重合闸", number = 1)
    private int BlockingRec;

    /**
     * 弹簧未储能
     */
    @ModelArguments(code = "SpringNotStoredEnergy", mean = "弹簧未储能", number = 2)
    private int SpringNotStoredEnergy;
    /**
     * 远方状态
     */
    @ModelArguments(code = "RemoteStatus", mean = "远方状态", number = 3)
    private int RemoteStatus;

    /**
     * 检修状态
     */
    @ModelArguments(code = "CheckStatus", mean = "检修状态", number = 4)
    private int CheckStatus;

    /**
     * 断路器合位
     */
    @ModelArguments(code = "startForWhole", mean = "断路器合位", number = 5)
    private int startForWhole;

    /**
     * 断路器分位
     */
    @ModelArguments(code = "CircuitBreakerClosing", mean = "断路器分位", number = 6)
    private int CircuitBreakerClosing;

    /**
     * 手车工作位置
     */
    @ModelArguments(code = "HandCartWorkingPos", mean = "手车工作位置", number = 7)
    private int HandCartWorkingPos;

    /**
     * 手车试验位置
     */
    @ModelArguments(code = "HandCartTryPos", mean = "手车试验位置", number = 8)
    private int HandCartTryPos;
    /**
     * 接地刀合位
     */
    @ModelArguments(code = "GroundingSwitchClosePos", mean = "接地刀合位", number = 9)
    private int GroundingSwitchClosePos;

    /**
     * 事故总
     */
    @ModelArguments(code = "TotalNumberAccidents", mean = "事故总", number = 10)
    private int TotalNumberAccidents;

    /**
     * 备用开入1
     */
    @ModelArguments(code = "BackupInput1", mean = "备用开入1", number = 11)
    private int BackupInput1;

    /**
     * 备用开入2
     */
    @ModelArguments(code = "BackupInput2", mean = "备用开入2", number = 12)
    private int BackupInput2;

    /**
     * 备用开入3
     */
    @ModelArguments(code = "BackupInput3", mean = "备用开入3", number = 13)
    private int BackupInput3;

    /**
     * 备用开入4
     */
    @ModelArguments(code = "BackupInput4", mean = "备用开入4", number = 14)
    private int BackupInput4;
    /**
     * 备用开入5
     */
    @ModelArguments(code = "BackupInput5", mean = "备用开入5", number = 15)
    private int BackupInput5;

    /**
     *备用开入6
     */
    @ModelArguments(code = "BackupInput6", mean = "备用开入6", number = 16)
    private int BackupInput6;

    /**
     *备用开入7
     */
    @ModelArguments(code = "BackupInput7", mean = "备用开入7", number = 17)
    private int BackupInput7;

    /**
     *BackupInput8
     */
    @ModelArguments(code = "BackupInput8", mean = "备用开入8", number = 18)
    private int BackupInput8;
    /**
     *备用开入6
     */
    @ModelArguments(code = "BackupInput9", mean = "备用开入9", number = 19)
    private int BackupInput9;

    /**
     *BackupInput10
     */
    @ModelArguments(code = "BackupInput10", mean = "备用开入10", number = 20)
    private int BackupInput10;

    /**
     *BackupInput11
     */
    @ModelArguments(code = "BackupInput11", mean = "备用开入11", number = 21)
    private int BackupInput11;

    /**
     *BackupInput12
     */
    @ModelArguments(code = "BackupInput12", mean = "备用开入12", number = 22)
    private int BackupInput12;

    public int getBlockingRec() {
        return BlockingRec;
    }

    public void setBlockingRec(int blockingRec) {
        BlockingRec = blockingRec;
    }

    public int getSpringNotStoredEnergy() {
        return SpringNotStoredEnergy;
    }

    public void setSpringNotStoredEnergy(int springNotStoredEnergy) {
        SpringNotStoredEnergy = springNotStoredEnergy;
    }

    public int getRemoteStatus() {
        return RemoteStatus;
    }

    public void setRemoteStatus(int remoteStatus) {
        RemoteStatus = remoteStatus;
    }

    public int getCheckStatus() {
        return CheckStatus;
    }

    public void setCheckStatus(int checkStatus) {
        CheckStatus = checkStatus;
    }

    public int getStartForWhole() {
        return startForWhole;
    }

    public void setStartForWhole(int startForWhole) {
        this.startForWhole = startForWhole;
    }

    public int getCircuitBreakerClosing() {
        return CircuitBreakerClosing;
    }

    public void setCircuitBreakerClosing(int circuitBreakerClosing) {
        CircuitBreakerClosing = circuitBreakerClosing;
    }

    public int getHandCartWorkingPos() {
        return HandCartWorkingPos;
    }

    public void setHandCartWorkingPos(int handCartWorkingPos) {
        HandCartWorkingPos = handCartWorkingPos;
    }

    public int getHandCartTryPos() {
        return HandCartTryPos;
    }

    public void setHandCartTryPos(int handCartTryPos) {
        HandCartTryPos = handCartTryPos;
    }

    public int getGroundingSwitchClosePos() {
        return GroundingSwitchClosePos;
    }

    public void setGroundingSwitchClosePos(int groundingSwitchClosePos) {
        GroundingSwitchClosePos = groundingSwitchClosePos;
    }

    public int getTotalNumberAccidents() {
        return TotalNumberAccidents;
    }

    public void setTotalNumberAccidents(int totalNumberAccidents) {
        TotalNumberAccidents = totalNumberAccidents;
    }

    public int getBackupInput1() {
        return BackupInput1;
    }

    public void setBackupInput1(int backupInput1) {
        BackupInput1 = backupInput1;
    }

    public int getBackupInput2() {
        return BackupInput2;
    }

    public void setBackupInput2(int backupInput2) {
        BackupInput2 = backupInput2;
    }

    public int getBackupInput3() {
        return BackupInput3;
    }

    public void setBackupInput3(int backupInput3) {
        BackupInput3 = backupInput3;
    }

    public int getBackupInput4() {
        return BackupInput4;
    }

    public void setBackupInput4(int backupInput4) {
        BackupInput4 = backupInput4;
    }

    public int getBackupInput5() {
        return BackupInput5;
    }

    public void setBackupInput5(int backupInput5) {
        BackupInput5 = backupInput5;
    }

    public int getBackupInput6() {
        return BackupInput6;
    }

    public void setBackupInput6(int backupInput6) {
        BackupInput6 = backupInput6;
    }

    public int getBackupInput7() {
        return BackupInput7;
    }

    public void setBackupInput7(int backupInput7) {
        BackupInput7 = backupInput7;
    }

    public int getBackupInput8() {
        return BackupInput8;
    }

    public void setBackupInput8(int backupInput8) {
        BackupInput8 = backupInput8;
    }

    public int getBackupInput9() {
        return BackupInput9;
    }

    public void setBackupInput9(int backupInput9) {
        BackupInput9 = backupInput9;
    }

    public int getBackupInput10() {
        return BackupInput10;
    }

    public void setBackupInput10(int backupInput10) {
        BackupInput10 = backupInput10;
    }

    public int getBackupInput11() {
        return BackupInput11;
    }

    public void setBackupInput11(int backupInput11) {
        BackupInput11 = backupInput11;
    }

    public int getBackupInput12() {
        return BackupInput12;
    }

    public void setBackupInput12(int backupInput12) {
        BackupInput12 = backupInput12;
    }
}
