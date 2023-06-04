package com.ntdq.power_station.redis.queue;

import com.ntdq.power_station.domain.powerStation.PowerStationReport;
import com.ntdq.power_station.redis.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

//@Component
public class ReportQueue implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(ReportQueue.class);

    private static final ConcurrentHashMap<String, LinkedBlockingQueue<PowerStationReport>> stationReportMap = new ConcurrentHashMap<>();

    @Autowired
    private RedisClient redisClient;

    /**
     * 添加数据到队列中
     * @param deviceId
     * @param value
     */
    public static void addDeviceReportData(String deviceId,PowerStationReport value){
        LinkedBlockingQueue<PowerStationReport> powerStationReports = stationReportMap.get(deviceId);
        if (powerStationReports==null){
            powerStationReports = new LinkedBlockingQueue<>();
            stationReportMap.put(deviceId,powerStationReports);
        }
        powerStationReports.add(value);
    }

    /**
     * 开启线程持续坚挺任务队列 有数据存到redis中
     * 轮训检查是否有新数据
     * @param args incoming application arguments
     */
    @Override
    public void run(ApplicationArguments args){
        logger.info("开启存入数据redis线程任务...");
        while (true) {
            for (Map.Entry<String, LinkedBlockingQueue<PowerStationReport>> longLinkedBlockingQueueEntry : stationReportMap.entrySet()) {
                String key = longLinkedBlockingQueueEntry.getKey();
                LinkedBlockingQueue<PowerStationReport> queue = longLinkedBlockingQueueEntry.getValue();
                String powerStationPoint = redisClient.getKeyForStrValue(key);
                do {
                    PowerStationReport powerStationReport = queue.poll();
                    if (powerStationReport != null) {
                        powerStationPoint = powerStationPoint == null ? "第一次拿到redis数据..." : powerStationPoint + "," + powerStationReport;
                    }
                } while (!queue.isEmpty());
                redisClient.setKeyForStrValue(key, powerStationPoint);
            }
        }
    }
}
