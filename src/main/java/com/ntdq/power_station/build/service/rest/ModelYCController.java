package com.ntdq.power_station.build.service.rest;

import com.ntdq.power_station.build.service.domain.entity.ModelYCAndYX;
import com.ntdq.power_station.build.service.mapper.ModelYCMapper;
import com.ntdq.power_station.common.annotation.ModelArguments;
import com.ntdq.power_station.common.annotation.ModelDeviceInfo;
import com.ntdq.power_station.common.rest.RestResponse;
import com.ntdq.power_station.domain.PointConfiguration;
import com.ntdq.power_station.domain.engrgy.yc.EnergyBatteryGroupExtra;
import com.ntdq.power_station.domain.engrgy.yc.EnergyBatteryStackExtra;
import com.ntdq.power_station.domain.engrgy.yx.EnergyBatterGroupYx;
import com.ntdq.power_station.mapper.ConfigurationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/modelYC")
public class ModelYCController {

    @Autowired(required = false)
    private ModelYCMapper modelYCMapper;


    @Autowired
    private ConfigurationMapper configurationMapper;

    @RequestMapping("/addBatteryVoltage")
    public RestResponse<ModelYCAndYX> saveEnergyBatteryVoltage() {
        List<ModelYCAndYX> modelYCAndYXES = new ArrayList<>();
        for (int i = 1; i <= 313; i++) {
            ModelYCAndYX modelYCAndYX = new ModelYCAndYX();
            modelYCAndYX.setCommonModelId(3);
            modelYCAndYX.setMpNo(i - 1);
            modelYCAndYX.setMpName("电池" + i + "电压");
            modelYCAndYX.setMpCode("batteryVoltage" + i);
            modelYCAndYX.setDevMainType(7);
            modelYCAndYX.setDevSubType(11);
            modelYCAndYXES.add(modelYCAndYX);
        }
        modelYCMapper.saveBatchModelYC(modelYCAndYXES);
        return RestResponse.createSuccess("添加成功", null);
    }

    @RequestMapping("/addEnergyGroupInfo")
    public RestResponse<ModelYCAndYX> saveEnergyBatteryGroupYX() {
        EnergyBatterGroupYx energyBatteryGroupExtra = new EnergyBatterGroupYx();
        Class<? extends EnergyBatterGroupYx> energyBatteryGroupExtraClass = energyBatteryGroupExtra.getClass();
        ModelDeviceInfo modelDeviceInfo = energyBatteryGroupExtraClass.getAnnotation(ModelDeviceInfo.class);
        int commonModelId = modelDeviceInfo.modelType();
        int devMainType = modelDeviceInfo.mainType();
        int devSubtype = modelDeviceInfo.subType();
        List<ModelYCAndYX> modelYCAndYXES = new ArrayList<>();
        for (Field field : energyBatteryGroupExtraClass.getDeclaredFields()) {
            ModelArguments modelArguments = field.getAnnotation(ModelArguments.class);
            if (modelArguments != null) {
                ModelYCAndYX modelYCAndYX = new ModelYCAndYX();
                modelYCAndYX.setCommonModelId(commonModelId);
                modelYCAndYX.setDevMainType(7);
                modelYCAndYX.setDevSubType(9);
                modelYCAndYX.setMpNo(modelArguments.number());
                modelYCAndYX.setMpName(modelArguments.mean());
                modelYCAndYX.setMpCode(modelArguments.code());
                modelYCAndYXES.add(modelYCAndYX);
            }
        }
        modelYCMapper.saveBatchModelYX(modelYCAndYXES);
        return RestResponse.createSuccess("添加成功", null);
    }

    @RequestMapping("/addGroupInfo")
    public RestResponse<ModelYCAndYX> saveEnergyBatteryGroupExtra() {
        EnergyBatteryGroupExtra energyBatteryGroupExtra = new EnergyBatteryGroupExtra();
        Class<? extends EnergyBatteryGroupExtra> energyBatteryGroupExtraClass = energyBatteryGroupExtra.getClass();
        ModelDeviceInfo modelDeviceInfo = energyBatteryGroupExtraClass.getAnnotation(ModelDeviceInfo.class);
        int commonModelId = modelDeviceInfo.modelType();
        int devMainType = modelDeviceInfo.mainType();
        int devSubtype = modelDeviceInfo.subType();
        List<ModelYCAndYX> modelYCAndYXES = new ArrayList<>();
        for (Field field : energyBatteryGroupExtraClass.getDeclaredFields()) {
            ModelArguments modelArguments = field.getAnnotation(ModelArguments.class);
            if (modelArguments != null) {
                ModelYCAndYX modelYCAndYX = new ModelYCAndYX();
                modelYCAndYX.setCommonModelId(commonModelId);
                modelYCAndYX.setDevMainType(devMainType);
                modelYCAndYX.setDevSubType(devSubtype);
                modelYCAndYX.setMpNo(modelArguments.number());
                modelYCAndYX.setMpName(modelArguments.mean());
                modelYCAndYX.setMpCode(modelArguments.code());
                modelYCAndYXES.add(modelYCAndYX);
            }
        }
        modelYCMapper.saveBatchModelYX(modelYCAndYXES);
        return RestResponse.createSuccess("添加成功", null);
    }

    @RequestMapping("/addStackInfo")
    public RestResponse<ModelYCAndYX> saveEnergyBatteryStackExtra() {
        EnergyBatteryStackExtra energyBatteryStackExtra = new EnergyBatteryStackExtra();
        Class<? extends EnergyBatteryStackExtra> energyBatteryStackExtraClass = energyBatteryStackExtra.getClass();
        ModelDeviceInfo modelDeviceInfo = energyBatteryStackExtraClass.getAnnotation(ModelDeviceInfo.class);
        int commonModelId = modelDeviceInfo.modelType();
        int devMainType = modelDeviceInfo.mainType();
        int devSubtype = modelDeviceInfo.subType();
        List<ModelYCAndYX> modelYCAndYXES = new ArrayList<>();
        for (Field field : energyBatteryStackExtraClass.getDeclaredFields()) {
            ModelArguments modelArguments = field.getAnnotation(ModelArguments.class);
            if (modelArguments != null) {
                ModelYCAndYX modelYCAndYX = new ModelYCAndYX();
                modelYCAndYX.setCommonModelId(commonModelId);
                modelYCAndYX.setDevMainType(devMainType);
                modelYCAndYX.setDevSubType(devSubtype);
                modelYCAndYX.setMpNo(modelArguments.number());
                modelYCAndYX.setMpName(modelArguments.mean());
                modelYCAndYX.setMpCode(modelArguments.code());
                modelYCAndYXES.add(modelYCAndYX);
            }
        }
        modelYCMapper.saveBatchModelYC(modelYCAndYXES);
        return RestResponse.createSuccess("添加成功", null);
    }

    @RequestMapping("/saveYCAndYX")
    public RestResponse<ModelYCAndYX> saveYCAndYX(int type, String classUrl) throws ClassNotFoundException {
        Class<?> pointClass = Class.forName(classUrl);
//        EnergyInverter energyInverter = new EnergyInverter();
//        Class<? extends EnergyInverter> energyInverterClass = energyInverter.getClass();
        ModelDeviceInfo modelDeviceInfo = pointClass.getAnnotation(ModelDeviceInfo.class);
        int commonModelId = modelDeviceInfo.modelType();
        int devMainType = modelDeviceInfo.mainType();
        int devSubType = modelDeviceInfo.subType();
        List<ModelYCAndYX> modelYCAndYXES = new ArrayList<>();
        for (Field field : pointClass.getDeclaredFields()) {
            ModelArguments modelArguments = field.getAnnotation(ModelArguments.class);
            if (modelArguments != null) {
                ModelYCAndYX modelYCAndYX = new ModelYCAndYX();
                modelYCAndYX.setCommonModelId(commonModelId);
                modelYCAndYX.setDevMainType(devMainType);
                modelYCAndYX.setDevSubType(devSubType);
                modelYCAndYX.setMpNo(modelArguments.number());
                modelYCAndYX.setMpName(modelArguments.mean());
                modelYCAndYX.setMpCode(modelArguments.code());
                modelYCAndYXES.add(modelYCAndYX);
            }
        }
        int count = 0;
        switch (type) {
            case 0:
                count = modelYCMapper.saveBatchModelYC(modelYCAndYXES);
                break;
            case 1:
                count = modelYCMapper.saveBatchModelYX(modelYCAndYXES);
                break;
        }
        return RestResponse.createSuccess("添加配置成功,数量为:" + count, null);
    }

    @RequestMapping("/addDevice")
    public RestResponse<String> addDevice(Integer address, int type, String deviceName, Integer mainType, Integer subType) {
        List<ModelYCAndYX> modelListByType = null;
        switch (type) {
            case 0:
                modelListByType = modelYCMapper.findModelListByYCType(mainType, subType);
                break;
            case 1:
                modelListByType = modelYCMapper.findModelListByYXType(mainType, subType);
                break;
        }
        List<PointConfiguration> pointConfigurations = new LinkedList<>();
        for (ModelYCAndYX modelYCAndYX : modelListByType) {
            PointConfiguration pointConfiguration = new PointConfiguration();
            pointConfiguration.setAddress(address + modelYCAndYX.getMpNo() - 1);
            pointConfiguration.setFieldName(modelYCAndYX.getMpName());
            pointConfiguration.setFieldCode(modelYCAndYX.getMpCode());
            pointConfiguration.setDeviceType(modelYCAndYX.getDevMainType());
            pointConfiguration.setDeviceSubType(modelYCAndYX.getDevSubType());
            pointConfiguration.setDeviceName(deviceName);
            pointConfigurations.add(pointConfiguration);
        }
        int count = 0;
        switch (type) {
            case 0:
                count = configurationMapper.savePointYCConfigBatch(pointConfigurations);
                break;
            case 1:
                count = configurationMapper.savePointYXConfigBatch(pointConfigurations);
                break;
        }

        return RestResponse.createSuccess("成功插入" + count + "条数据", null);
    }

    public static void main(String[] args) {
        ModelYCAndYX modelYCAndYX = new ModelYCAndYX();
        Class<? extends ModelYCAndYX> aClass = modelYCAndYX.getClass();
        Field[] fields = aClass.getFields();
        System.out.println(fields);
    }
}
