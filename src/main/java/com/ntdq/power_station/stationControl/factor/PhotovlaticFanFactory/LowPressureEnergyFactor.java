package com.ntdq.power_station.stationControl.factor.PhotovlaticFanFactory;

import com.ntdq.power_station.stationControl.PhotovoltaicFanStrategyContext;
import org.springframework.stereotype.Component;

/**
 * 我如何讲这些executeTheActionOnCondition的返回结果拿出来 汇总
 * 然后决定执行那种策略呢
 */
@Component
public class LowPressureEnergyFactor extends PhotovoltaicFanStrategyContext {

    /**
     * 查询Redis实时数据是否满足当前次因素条件
     * @return
     */
    @Override
    protected boolean IsOnCondition() {
        return false;
    }

    public static void main(String[] args) {
        LowPressureEnergyFactor lowPressureEnergyFactor = new LowPressureEnergyFactor();
        int i = lowPressureEnergyFactor.executeTheActionOnCondition();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        startJudgeCurrentResult();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
