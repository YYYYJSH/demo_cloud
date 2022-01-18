package com.yjsh.utils;

import org.slf4j.MDC;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 异步工具
 *
 * @author 殷金帅
 * @date 2021/12/15
 */
public class AsyncBatchCallUtil {
    private static final String TRACE_ID = "traceId";

    /**
     * 对外界提供方法
     */
    public static void execute(Runnable task){
        executorPool.execute(task);
    }

    /**
     * 线程池服务
     */
    private static ExecutorService executorPool;

    static{
        //线程池核心数，也是线程池最少维护的线程数量
        int corePoolSize=5;
        //线程池维护最大线程数
        int maxPoolSize=20;
        //线程池维护的线程的最大空闲时间
        long keepAliveTime=30;
        //时间单位
        TimeUnit unit=TimeUnit.SECONDS;
        //队列
        BlockingQueue<Runnable> workQueue=new ArrayBlockingQueue<>(6);
        //线程工厂
        ThreadFactory threadFactory=new UserThreadFactory("myjk-api-activity-thread");
        //拒绝
        RejectedExecutionHandler handler=new MyRejectPolicy();
        //实例化线程池
        executorPool=new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveTime,
                unit,workQueue,threadFactory,handler);
    }

    /**
     * 拒绝策略
     */
    static class MyRejectPolicy implements RejectedExecutionHandler{
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(AsyncBatchCallUtil.class.getName()+"的线程队列满了");
        }
    }

    /**
     * 线程工厂
     */
    static class UserThreadFactory implements ThreadFactory{
        public static final String LOG_ASYNC_POOL = " myjkApiActivityAsyncThreadPool ";
        private final String namePrefix;
        private final String trace;
        private final AtomicInteger nextId=new AtomicInteger(1);
        UserThreadFactory(String groupName){
            namePrefix=UserThreadFactory.class.getName()+LOG_ASYNC_POOL;
            trace = MDC.get(TRACE_ID);
        }
        @Override
        public Thread newThread(Runnable r) {
            String name=namePrefix+"-"+nextId.getAndIncrement();
            Thread thread=new Thread(null,r,name);
            MDC.put(TRACE_ID,trace);
            return thread;
        }

    }
}
