package com.ntdq.power_station.config.netDomain.tcp;

import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.domain.PointConfiguration;
import com.ntdq.power_station.nettyHandler.parseCore.parse.Energy104Parser;
import com.ntdq.power_station.nettyHandler.parseCore.parse.IParse;
import com.ntdq.power_station.mapper.ConfigurationMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Energy104ClientBOBase extends ServerBaseConfiguration implements InitializingBean {
    @Value("${104.client.energy.addr}")
    private String Addr104;


    @Value("${104.client.energy.port}")
    private int Port104;

    private Long CurrentThreadId;

    @Autowired
    private Energy104Parser energy104Parser;

    @Autowired
    private ConfigurationMapper configurationMapper;

    private Map<Integer, String> addressFieldNameMap;

    @Override
    public IParse getParse() {
        return this.energy104Parser;
    }

    public Map<Integer, String> getAddressFieldNameMap() {
        return addressFieldNameMap;
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

    public void setEnergy104Parser(Energy104Parser energy104Parser) {
        this.energy104Parser = energy104Parser;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<PointConfiguration> energyConfigList = configurationMapper.getAllConfigByYC(7);
        List<PointConfiguration> InvertYX = configurationMapper.getAllConfigByYX(7);
        //List<PointConfiguration> BatteryGroupYX = configurationMapper.getAllConfigByYX(8);
        energyConfigList.addAll(InvertYX);
        addressFieldNameMap = energyConfigList.stream()
                .collect(Collectors.toMap(
                        PointConfiguration::getAddress,
                        PointConfiguration::getFieldName
                ));
//        addressFieldNameMap = energyConfigList.stream().collect(Collectors.toMap(PointConfiguration::getAddress, PointConfiguration::getFieldName));
        energy104Parser.setAddressFieldMap(addressFieldNameMap);
    }
}
