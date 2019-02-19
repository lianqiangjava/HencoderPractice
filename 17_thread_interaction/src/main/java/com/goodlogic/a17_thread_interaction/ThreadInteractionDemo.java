package com.goodlogic.a17_thread_interaction;

public class ThreadInteractionDemo implements TestDemo{

    @Override
    public void runTest() {
        Thread thread = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    if (Thread.interrupted()){//此方法会重置interrupted
                        //收尾
                        return;
                    }
                    try {
                        Thread.sleep(2000);
                        //此处异常会重置interrupted
                    }catch (InterruptedException e){//当线程在睡眠过程中，外部调用了interrupt，就会唤醒线程并抛出异常达到打断线程目的，可以不做处理
                        //收尾
                        return;//可以不做处理
                    }
//                    SystemClock.sleep(2000); //Android里sleep,它隐藏了异常处理，不会被打断
                    System.out.println("number: "+ i);
                }
            }
        };
        thread.start();
        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        thread.interrupt();//通知线程可以中断了，不是立即中断，一般用于节省资源
    }

}
