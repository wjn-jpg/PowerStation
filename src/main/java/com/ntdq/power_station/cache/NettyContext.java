package com.ntdq.power_station.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * Netty 程序额外开启的线程
 */
public class NettyContext {
    /**
     * 线程容器
     */
    private static final ConcurrentHashMap<String, List<Thread>> nettyThreadListMap = new ConcurrentHashMap<>();

    /**
     * 线程池容器
     */
    private static final ConcurrentHashMap<String, List<ExecutorService>> nettyThreadPoolMap = new ConcurrentHashMap<>();

    public static void addThreadMapByNetty(String nettyGroup, Thread thread) {
        List<Thread> threads = nettyThreadListMap.computeIfAbsent(nettyGroup, k -> new ArrayList<>());
        threads.add(thread);
    }

    public static void addThreadPoolMapByNetty(String nettyGroup, ExecutorService executorService) {
        List<ExecutorService> executorServices = nettyThreadPoolMap.computeIfAbsent(nettyGroup, k -> new ArrayList<>());
        executorServices.add(executorService);
    }

    public static boolean removeExtraThreadPool(String nettyGroup) {
        List<ExecutorService> executorServices = nettyThreadPoolMap.get(nettyGroup);
        if (executorServices != null) {
            executorServices.forEach(ExecutorService::shutdown);
        }
        return nettyThreadPoolMap.remove(nettyGroup, executorServices);
    }

    public static boolean removeExtraThread(String nettyGroup) {
        List<Thread> threadList = nettyThreadListMap.get(nettyGroup);
        for (Thread thread : threadList) {
            thread.interrupt();
        }
        return nettyThreadListMap.remove(nettyGroup, threadList);
    }
}
