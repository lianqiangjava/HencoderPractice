package com.goodlogic.a16_thread_synchronization;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Synchronized3Demo implements TestDemo {

    private int x = 0;
    private int y = 0;
    private String name;
    private volatile int a = 0;
    private AtomicInteger c = new AtomicInteger(0);
    private final Object monitor1 = new Object();
    private final Object monitor2 = new Object();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


//    private void count(int newValue){
//        synchronized (monitor1){
//            x = newValue;
//            y = newValue;
//        }
//    }

    //写锁时不允许其他线程写和读
    private void count(int newValue){
        writeLock.lock();
        try {
            x = newValue;
            y = newValue;
        }finally {
            writeLock.unlock();
        }
    }

    //读锁时不允许写，但是可以同时读
    private void print(){
        readLock.lock();
        try {
            System.out.println("value: "+ x + ", " + y);
        }finally {
            readLock.unlock();
        }
    }

    private void munus(int delta){
        synchronized (monitor1){
            x -= delta;
            y -= delta;
        }
    }

    private void setName(String newName){
        synchronized (monitor2){
            name = newName;
        }
    }

    @Override
    public void runTest() {

    }
}
