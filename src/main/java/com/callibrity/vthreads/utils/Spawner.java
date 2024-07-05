package com.callibrity.vthreads.utils;

import java.util.HashSet;
import java.util.Set;

public class Spawner implements AutoCloseable {

    private final Thread.Builder threadBuilder;
    private final Set<Thread> threads = new HashSet<>();

    public static Spawner ofVirtual() {
        return new Spawner(Thread.ofVirtual());
    }

    private Spawner(Thread.Builder threadBuilder) {
        this.threadBuilder = threadBuilder;
    }

    public void spawn(String name, Runnable runnable) {
        threads.add(threadBuilder.name(name).start(runnable));
    }

    public void close() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                thread.interrupt();
            }
        }
    }
}
