package com.ntdq.power_station.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModelDeviceInfo {

    int mainType() default 0;

    int modelType() default 0;

    int subType() default 0;

}
