package com.callibrity.vthreads.examples;

public class CreatingWithBuilder {

    public static void main(String[] args) throws Exception {
        Thread thread = Thread.ofVirtual().start(() -> System.out.println("Hello, World!"));
        thread.join();
    }
}
