package com.goodlogic.a17_thread_interaction;

public class Main {

    public static void main(String[] args){
//        runWaitDemo();
        customizableThread();
    }

    static void runWaitDemo(){
        new WaitDemo().runTest();
    }
    static void customizableThread(){
        new CustomizableThreadDemo().runTest();
    }
}
