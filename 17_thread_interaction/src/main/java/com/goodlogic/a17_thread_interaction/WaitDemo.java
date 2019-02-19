package com.goodlogic.a17_thread_interaction;

public class WaitDemo implements TestDemo{
    private String sharedString;

    private synchronized void initString(){
        sharedString = "rengwuxian";
        notifyAll();//通知等待队列
    }

    private synchronized void printString(){
        while (sharedString == null){
            try {
                wait();//暂时释放当前对象的monitor，进入等待队列
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            //从这执行

        }
        System.out.println("String: "+ sharedString);
    }


    @Override
    public void runTest() {
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                printString();
            }
        };
        thread1.start();
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                initString();
            }
        };
        thread2.start();
    }
}
