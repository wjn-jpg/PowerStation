package com.ntdq.power_station.config.netDomain.tcp;

import com.ntdq.power_station.config.netDomain.base.ServerBaseConfiguration;
import com.ntdq.power_station.domain.PointConfiguration;
import com.ntdq.power_station.mapper.ConfigurationMapper;
import com.ntdq.power_station.nettyHandler.parseCore.parse.Distribution104Parser;
import com.ntdq.power_station.nettyHandler.parseCore.parse.IParse;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 变电104配置类 每个104配置累都要有对应的解析器和点表map
 */
@Component
public class Distribution104ClientBOBase extends ServerBaseConfiguration implements InitializingBean {

    @Value("${104.client.distribution.addr}")
    private String Addr104;


    @Value("${104.client.distribution.port}")
    private int Port104;

    @Autowired
    private Distribution104Parser distribution104Parser;

    @Autowired
    private ConfigurationMapper configurationMapper;

    private Map<Integer, String> addressFieldNameMap;

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

    public Distribution104Parser getDistribution104Parser() {
        return distribution104Parser;
    }

    public void setDistribution104Parser(Distribution104Parser distribution104Parser) {
        this.distribution104Parser = distribution104Parser;
    }

    public Map<Integer, String> getAddressFieldNameMap() {
        return addressFieldNameMap;
    }

    public void setAddressFieldNameMap(Map<Integer, String> addressFieldNameMap) {
        this.addressFieldNameMap = addressFieldNameMap;
    }

    @Override
    public IParse getParse() {
        return this.distribution104Parser;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<PointConfiguration> energyConfigList = configurationMapper.getAllConfigByYC(2);
        addressFieldNameMap = energyConfigList.stream().collect(Collectors.toMap(PointConfiguration::getAddress, PointConfiguration::getFieldName));
        distribution104Parser.setAddressFieldMap(addressFieldNameMap);
    }
}
