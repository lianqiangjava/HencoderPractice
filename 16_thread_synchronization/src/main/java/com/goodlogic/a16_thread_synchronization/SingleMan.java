package com.goodlogic.a16_thread_synchronization;

public class SingleMan {
    private static SingleMan ourInstance;

    private SingleMan(){

    }

    static SingleMan newInstance(){
        synchronized (SingleMan.class){
            if (ourInstance == null){
                synchronized (SingleMan.class){
                    if (ourInstance == null){
                        ourInstance = new SingleMan();
                    }
                }
            }
            return ourInstance;
        }
    }
}
