package com.ntdq.power_station.stationControl.factor.PhotovlaticFanFactory;

import com.ntdq.power_station.redis.RedisClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public abstract class RedisFactorAdapter implements SingleFactor, InitializingBean {

    protected RedisClient redisClient;

    private int currentResult;

    /**
     * 1 标识是这种情况
     * 0 标识不是
     * @return
     */
    @Override
    public int executeTheActionOnCondition() {
        return currentResult;
    }

    @Autowired
    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    /**
     * 循环判断是否处于这种情况
     */
    protected void startJudgeCurrentResult() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (IsOnCondition()) {
                        currentResult = 1;
                    } else {
                        currentResult = 0;
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }


    protected abstract boolean IsOnCondition();

}
