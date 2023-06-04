package com.ntdq.power_station.stationControl;

import com.ntdq.power_station.stationControl.build.ActionOrder;
import com.ntdq.power_station.stationControl.factor.PhotovlaticFanFactory.RedisFactorAdapter;
import org.apache.kafka.common.protocol.types.Field;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 光伏风机基本策略
 */
@Component
public abstract class PhotovoltaicFanStrategyContext extends RedisFactorAdapter implements ActionOrder, ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

    private static final Set<Class<?>> PhotovoltaicFanStrategyClassList = new HashSet<>();

    private static final List<PhotovoltaicFanStrategyContext> context = new LinkedList<>();

    private static final Logger logger = LoggerFactory.getLogger(PhotovoltaicFanStrategyContext.class);

    private static ApplicationContext applicationContext;

    static {
        PhotovoltaicFanStrategyClassList.addAll(getAllChildClassExtendTheClass("com.ntdq.power_station.stationControl.factor", PhotovoltaicFanStrategyContext.class));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        PhotovoltaicFanStrategyContext.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("Bean加载完毕 开始执行相应调控策略...");
        for (Class<?> aClass : PhotovoltaicFanStrategyClassList) {
            context.add((PhotovoltaicFanStrategyContext) applicationContext.getBean(aClass));
        }
        startStrategicRegulation();
    }

    private void startStrategicRegulation() {
        new Thread(() -> {
            for (; ; ) {
                StringBuilder stringBuilder = new StringBuilder();
                for (PhotovoltaicFanStrategyContext photovoltaicFanStrategyContext : context) {
                    stringBuilder.append(photovoltaicFanStrategyContext.executeTheActionOnCondition());
                }
                String strategyKey = stringBuilder.toString();
                //执行对应的策略
            }
        }).start();
    }


    /**
     * 判断光伏风机策略的因素Map
     */
    private static final Map<String, Boolean> FactoryJudgeMap = new HashMap<>();

    protected void addTrueFactor(String order) {
        FactoryJudgeMap.put(order, Boolean.TRUE);
    }

    protected void addFalseFactor(String order) {
        FactoryJudgeMap.put(order, Boolean.FALSE);
    }

    public static Set<Class<?>> getAllChildClassExtendTheClass(String packName, Class<?> theClass) {
        Reflections reflections = new Reflections(packName);
        return (Set<Class<?>>) reflections.getSubTypesOf(theClass);
    }


    public static void main(String[] args) {
        Class<?> beanClass = PhotovoltaicFanStrategyContext.class;
        String packAgeName = "com.ntdq.power_station.stationControl.factor";
        Set<Class<?>> allChildClassExtendTheClass = getAllChildClassExtendTheClass(packAgeName, beanClass);
        System.out.println(allChildClassExtendTheClass);
    }

}
