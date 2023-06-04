package com.ntdq.power_station.nettyHandler.parseCore.handler;

import com.alibaba.fastjson.JSON;
import com.ntdq.power_station.domain.engrgy.yc.EnergyBatteryGroupExtra;
import com.ntdq.power_station.domain.engrgy.yc.EnergyBatteryStackExtra;
import com.ntdq.power_station.domain.engrgy.yc.EnergyInverter;
import com.ntdq.power_station.domain.engrgy.yc.NariProtectDeviceYC;
import com.ntdq.power_station.domain.engrgy.yx.NariProtectDeviceYX;
import com.ntdq.power_station.domain.photovoltaic.yc.PhotovoltaicYXExtra;
import com.ntdq.power_station.domain.photovoltaic.yc.PhotovoltaicInverter;
import com.ntdq.power_station.domain.photovoltaic.yx.NariMeasureControlDeviceYX;
import com.ntdq.power_station.nettyHandler.parseCore.entyty.PowerPoint;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * kafka转出处理地址数据 处理器
 *
 * @param <T> 要转发变为JSON的对象类型
 */
public class ForwardHandler<T> {

    private Map<Integer, String> addressFieldNameMap;

    private Object obj;

    public T parsePointData(int address, String value, int type) {
        return parsePointData(address, value, type, null);
    }

    public T parsePointData(int address, String value, int type, Object paramObj) {
        String methodName = addressFieldNameMap.get(address);
        if (methodName == null) {
            return paramObj == null ? (T) this.obj : (T) paramObj;
        }
        //自带的对象和传入的对象都为空
        if (paramObj == null && obj == null) {
            ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
            Type actualTypeArgument = parameterizedType.getActualTypeArguments()[0];
            Class<?> rowClass = getRowClass(actualTypeArgument);
            try {
                obj = rowClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        if (paramObj != null) {
            if (paramObj instanceof PhotovoltaicInverter) {
                PhotovoltaicInverter photovoltaicInverter = (PhotovoltaicInverter) paramObj;
                fillAllSetData(photovoltaicInverter.getClass(), methodName, photovoltaicInverter, value, type);
            } else if (paramObj instanceof EnergyInverter) {
                EnergyInverter energyInverter = (EnergyInverter) paramObj;
                fillAllSetData(energyInverter.getClass(), methodName, energyInverter, value, type);
            } else if (paramObj instanceof EnergyBatteryStackExtra) {
                EnergyBatteryStackExtra energyBatteryStackExtra = (EnergyBatteryStackExtra) paramObj;
                fillAllSetData(energyBatteryStackExtra.getClass(), methodName, energyBatteryStackExtra, value, type);
            } else if (paramObj instanceof EnergyBatteryGroupExtra) {
                EnergyBatteryGroupExtra energyBatteryGroupExtra = (EnergyBatteryGroupExtra) paramObj;
                fillAllSetData(energyBatteryGroupExtra.getClass(), methodName, energyBatteryGroupExtra, value, type);
            } else if (paramObj instanceof NariMeasureControlDeviceYX) { //光伏南瑞测控装置
                NariMeasureControlDeviceYX nariMeasureControlDeviceYX = (NariMeasureControlDeviceYX) paramObj;
                fillAllSetData(nariMeasureControlDeviceYX.getClass(), methodName, nariMeasureControlDeviceYX, value, type);
            } else if (paramObj instanceof NariProtectDeviceYC) { //储能南瑞保护YC
                NariProtectDeviceYC nariProtectDeviceYC = (NariProtectDeviceYC) paramObj;
                fillAllSetData(nariProtectDeviceYC.getClass(), methodName, nariProtectDeviceYC, value, type);
            } else if (paramObj instanceof NariProtectDeviceYX) { //储能南瑞保护YC
                NariProtectDeviceYX nariProtectDeviceYX = (NariProtectDeviceYX) paramObj;
                fillAllSetData(nariProtectDeviceYX.getClass(), methodName, nariProtectDeviceYX, value, type);
            }
        }
        else { //TODO 没有传入对象 用解析自己的对象 此分之一般不会进入 还未完善
            if (obj instanceof PhotovoltaicInverter) {
                PhotovoltaicInverter photovoltaicInverter = (PhotovoltaicInverter) obj;
                fillAllSetData(photovoltaicInverter.getClass(), methodName, photovoltaicInverter, value, type);
            }
        }

        return (T) (paramObj != null ? paramObj : obj);
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public void setObjNull() {
        this.obj = null;
    }

    private void fillAllSetData(Class<?> methodClass, String methodName, Object data, String value, int type) {
        for (Method method : methodClass.getMethods()) {
            if (method.getName().equalsIgnoreCase("set" + methodName)) {
                method.setAccessible(true);
                try {
                    switch (type) {
                        //yc
                        case 0: //yc
                            method.invoke(data, Float.parseFloat(value));
                            break;
                        //yx
                        case 1: //yx
                            method.invoke(data, Integer.parseInt(value));//Float.parseFloat(value)
                            break;
                    }
                    return;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private Class<?> getRowClass(Type type) {
        String[] classFileName = type.getTypeName().split("\\.");
        String className = classFileName[classFileName.length - 1];
        if (className.equalsIgnoreCase("PowerPoint")) {
            return PowerPoint.class;
        } else if (className.equalsIgnoreCase("PhotovoltaicExtra")) {
            return PhotovoltaicYXExtra.class;
        }
        return null;
    }

    public static void main(String[] args) {
        ForwardHandler<PowerPoint> girlForwardHandler = new ForwardHandler<PowerPoint>() {
        };
        try {
            PowerPoint girl = girlForwardHandler.parsePointData(1, null, 1);
            System.out.println(JSON.toJSON(girl));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, String> getAddressFieldNameMap() {
        return addressFieldNameMap;
    }

    public void setAddressFieldNameMap(Map<Integer, String> addressFieldNameMap) {
        this.addressFieldNameMap = addressFieldNameMap;
    }
}
