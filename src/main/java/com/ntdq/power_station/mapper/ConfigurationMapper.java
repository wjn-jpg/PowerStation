package com.ntdq.power_station.mapper;

import com.ntdq.power_station.domain.PointConfiguration;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConfigurationMapper {

    List<PointConfiguration> getAllConfigByYC(Integer devMainType);

    List<PointConfiguration> getAllConfigByYX(Integer devMainType);


    int savePointYXConfigBatch(List<PointConfiguration> pointConfigurations);

    int savePointYCConfigBatch(List<PointConfiguration> pointConfigurations);
}
