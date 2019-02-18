package com.goodlogic.a16_thread_synchronization;

public class Synchronized2Demo implements TestDemo {

    private int x = 0;
    private synchronized void count(){
        x++;
    }


    @Override
    public void runTest() {
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000_000; i++) {
                    count();
                }
                System.out.println("x from1:" + x);
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000_000; i++) {
                    count();
                }
                System.out.println("x from2:" + x);
            }
        }.start();
    }
}
