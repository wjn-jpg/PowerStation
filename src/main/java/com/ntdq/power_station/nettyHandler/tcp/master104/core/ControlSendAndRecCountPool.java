package com.ntdq.power_station.nettyHandler.tcp.master104.core;

import com.ntdq.power_station.cache.NettyContext;
import com.ntdq.power_station.cache.NettyThread;
import com.ntdq.power_station.domain.enumDo.DeviceEnum;
import com.ntdq.power_station.nettyHandler.tcp.master104.constant.Iec104Constant;
import com.ntdq.power_station.nettyHandler.tcp.master104.domain.MessageDetail;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wang_ji_nian
 * 控制计算接收和发送次数
 */
public class ControlSendAndRecCountPool {


    private static CachedThreadPool cachedThreadPool = new CachedThreadPool();

    private static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    private static final Logger logger = LoggerFactory.getLogger(ControlSendAndRecCountPool.class);

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

    /**
     * context.channel.write
     */
    private ChannelHandlerContext context;

    /**
     * 发送次数
     */
    private Short send;

    /**
     * 接收次数
     */
    private Short accept = 0;

    /**
     * 帧数量
     */
    private Short frameAmount = 0;

    /**
     * 对象锁
     */
    private final Boolean sendSFrameLock;

    /**
     * 一次发送的最大接收帧数
     */
    private final Short frameAmountMax = 1;

    private final ExecutorService cacheService = Executors.newCachedThreadPool();

    private final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    private static ReentrantLock LOCK = new ReentrantLock();

    private static Condition condition = LOCK.newCondition();

    private short lastSend;


    public ControlSendAndRecCountPool(ChannelHandlerContext context) {
        send = 0;
        accept = 0;
        frameAmount = 0;
        sendSFrameLock = true;
        this.context = context;
    }

    /**
     * 启动S发送确认帧的任务
     */
    public void startSendFrameTask1() {
        Runnable runnable = () -> {
            while (true) {
                logger.info("发送S确认帧...");
                try {
                    LOCK.lock();
                    //超过一次发送的最大帧就write
                    if (frameAmount >= frameAmountMax) {
                        MessageDetail messageDetail = new MessageDetail(Iec104Constant.S, accept);
                        logger.info("发送S帧!");
                        lastSend = accept;
                        context.channel().writeAndFlush(messageDetail);
                        frameAmount = 0;
                    }
                    //没发送一帧锁上 等待唤醒
                    logger.info("阻塞S帧锁... 序号:{}", accept);
                    condition.await();
                    logger.info("成功发送了S帧! 此时的序号为:{}", accept);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    LOCK.unlock();
                }
            }
        };
        cacheService.execute(runnable);
    }


    /**
     * 启动S发送确认帧的任务
     */
    public void startSendFrameTask(DeviceEnum deviceEnum) {
        Runnable runnable = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                logger.info("发送S确认帧...");
                try {
                    synchronized (sendSFrameLock) {
                        //超过一次发送的最大帧就write
                        if (frameAmount >= frameAmountMax) {
                            MessageDetail messageDetail = new MessageDetail(Iec104Constant.S, accept);
                            logger.info("发送S帧数据字节:{}", messageDetail.getSendData());
                            context.channel().writeAndFlush(messageDetail);
                            lastSend++;
                            frameAmount = 0;
                        }
                        //没发送一帧锁上 等待唤醒
                        logger.info("阻塞S帧锁... 序号:{}", accept);
                        sendSFrameLock.wait();
                        logger.info("成功发送了S帧! 此时的序号为:{}", accept);
                    }
                } catch (InterruptedException | NumberFormatException e) {
                    if (e instanceof InterruptedException){
                        logger.info("监听到Netty总线程关闭,关闭当前S帧线程:{}",Thread.currentThread().getName());
                        break;
                    }
                    ((NumberFormatException) e).printStackTrace();
                }
            }
        };
        NettyThread thread = new NettyThread(deviceEnum, runnable);
        thread.start();
    }

    public Short getAccept() {
        return accept;
    }

    /**
     * 设置接收序列号 并且更新接收count
     * 可以自己设置最大接收帧 每次到了最大接收帧 就发送一次
     *
     * @param lastAccept
     */
    public void setAccept(short lastAccept) {
        //必须要先加锁 防止发送任务线程还在执行 等待wait释放
//        System.out.println("发送Accept线程:" + Thread.currentThread().getName());
        synchronized (sendSFrameLock) {
            this.accept = lastAccept;
            frameAmount++;
            if (frameAmount >= frameAmountMax) {
                logger.info("序号:{}发送S帧数据 lastSend:{}", accept, this.lastSend);
                sendSFrameLock.notifyAll();
            }
        }
    }

    public short getSend() {
        synchronized (send) {
            short sendRule = this.send;
            this.send++;
            if (send > Iec104Constant.SEND_MAX) {
                send = Iec104Constant.SEND_MIN;
            }
            return sendRule;
        }
    }


    public static void main1(String[] args) {
//        int i = 100 + 1;
//        int data = i * 2;
//        String s = ByteUtil.byteArrayToHexString(ByteUtil.intToByteArray(data)).substring(4);
//        String high = s.substring(0, 2);
//        String low = s.substring(2);
//        System.out.println(high);
//        System.out.println(low);

//        String s = String.format("%04x", 11);
//        System.out.println(s);

        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        stringThreadLocal.set("sherry");
        new Thread(() -> {
            System.out.println(stringThreadLocal.get());
        }).start();


    }

    public static void main(String[] args) throws InterruptedException {
//        sendSFrameLock.notifyAll();
        System.out.println(singleThreadExecutor);
    }


}
