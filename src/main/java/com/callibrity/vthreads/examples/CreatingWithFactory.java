package com.callibrity.vthreads.examples;

import java.util.concurrent.ThreadFactory;

public class CreatingWithFactory {

    public static void main(String[] args) throws Exception {
        ThreadFactory factory = Thread.ofVirtual().factory();
        Thread thread = factory.newThread(() -> System.out.println("Hello, World!"));
        thread.start();
        thread.join();
    }
}
