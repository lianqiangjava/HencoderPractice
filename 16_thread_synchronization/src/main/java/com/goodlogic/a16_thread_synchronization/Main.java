package com.goodlogic.a16_thread_synchronization;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

//1.线程访问互斥 2.数据同步（内存数据拷贝与写回）
public class Main {
    public static void main(String[] args){
//        thread();
//        runnable();
//        threadFactory();
//        executor();
//        callable();
//        runSynchronized1Demo();
        runSynchronized2Demo();

    }

    /**
     * 使用Thread类来定义工作
     */
    static void thread(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("Thread started!");
            }
        };
        thread.start();
    }

    static void runnable(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread with Runnable started");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    static void threadFactory(){
        ThreadFactory factory = new ThreadFactory() {
            int count = 0;

            @Override
            public Thread newThread(Runnable runnable) {
                count++;
                return new Thread(runnable,"Tread-"+count);
            }
        };

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+ "started");
            }
        };
        Thread thread = factory.newThread(runnable);
        thread.start();
        Thread thread1 = factory.newThread(runnable);
        thread1.start();
    }

    static void executor(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread with Runnable started");
            }
        };
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(runnable);
        executor.execute(runnable);
        executor.execute(runnable);
    }

    static void callable(){
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(1500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                return "Done";
            }
        };
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<String> future = executor.submit(callable);
        try {
            String result = future.get();
            System.out.println("result: "+ result);
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }

    }

    static void runSynchronized1Demo(){
        new Synchronized1Demo().runTest();
    }

    static void runSynchronized2Demo(){
        new Synchronized2Demo().runTest();
    }

}
