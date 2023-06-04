package com.ntdq.power_station.config.netDomain.tcp;

import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.domain.PointConfiguration;
import com.ntdq.power_station.mapper.ConfigurationMapper;
import com.ntdq.power_station.nettyHandler.parseCore.parse.IParse;
import com.ntdq.power_station.nettyHandler.parseCore.parse.Photovoltaic104Parser;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PhotovoltaicServerBOBase extends ServerBaseConfiguration implements InitializingBean {

    @Value("${104.server.photovoltaic.addr}")
    private String Addr104;


    @Value("${104.server.photovoltaic.port}")
    private int Port104;

    private Long CurrentThreadId;

    @Autowired
    private Photovoltaic104Parser photovoltaic104Parser;

    @Autowired
    private ConfigurationMapper configurationMapper;

    private Map<Integer, String> addressFieldNameMap;

    @Override
    public IParse getParse() {
        return this.photovoltaic104Parser;
    }

    public String getAddr104() {
        return Addr104;
    }

    public void setAddr104(String addr104) {
        Addr104 = addr104;
    }

    public int getPort104() {
        return Port104;
    }

    public void setPort104(int port104) {
        Port104 = port104;
    }

    public Long getCurrentThreadId() {
        return CurrentThreadId;
    }

    public void setCurrentThreadId(Long currentThreadId) {
        CurrentThreadId = currentThreadId;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<PointConfiguration> energyYCList = configurationMapper.getAllConfigByYC(2);
        List<PointConfiguration> energyYXList = configurationMapper.getAllConfigByYX(2);
        addressFieldNameMap = energyYCList.stream().collect(Collectors.toMap(PointConfiguration::getAddress, PointConfiguration::getFieldName));
        Map<Integer, String> addressFieldNameMap_ = new HashMap<>();
        addressFieldNameMap_ = energyYXList.stream().collect(Collectors.toMap(PointConfiguration::getAddress, PointConfiguration::getFieldName));
        addressFieldNameMap.putAll(addressFieldNameMap_);
        photovoltaic104Parser.setAddressFieldMap(addressFieldNameMap);
    }
}
