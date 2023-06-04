package com.ntdq.power_station.build.service.mapper;

import com.ntdq.power_station.build.service.domain.entity.ModelYCAndYX;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ModelYCMapper {

    int saveBatchModelYC(List<ModelYCAndYX> modelYCAndYXES);

    int saveBatchModelYX(List<ModelYCAndYX> modelYCAndYXES);

    List<ModelYCAndYX> findModelListByYCType(int mainType, int subType);

    List<ModelYCAndYX> findModelListByYXType(int mainType, int subType);
}
