package com.terrygjt.mysqlite;

import android.os.AsyncTask;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by terry on 2019-05-25.
 * ThreadPoolExecutor 参数介绍
 * corePoolSize    int 核心线程池大小
 * maximumPoolSize int 最大线程池大小
 * keepAliveTime   long 线程最大空闲时间
 * unit            TimeUnit 时间单位
 * workQueue       BlockingQueue<Runnable> 线程等待队列
 * threadFactory   ThreadFactory 线程创建工厂
 * handler         RejectedExecutionHandler 拒绝策略
 */

public class B extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {

        return null;
    }
}
