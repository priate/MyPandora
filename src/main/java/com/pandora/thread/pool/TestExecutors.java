package com.pandora.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.pandora.thread.basis.WorkerThread;

/**
 * 线程池的一种使用方式 
 * @author C.H.
 * @CreateTime 2016年6月6日 上午12:05:38
 */
public class TestExecutors {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
        	Runnable worker = new WorkerThread("" + i);
            executor.execute(worker);
        }
//        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }
}
