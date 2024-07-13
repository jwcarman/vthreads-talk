package com.callibrity.vthreads.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Spawner implements AutoCloseable {
    private final ExecutorService executorService;

    public static Spawner ofVirtual() {
        return new Spawner(Thread.ofVirtual());
    }

    public static Spawner ofVirtual(String prefix) {
        return new Spawner(Thread.ofVirtual().name(prefix, 0));
    }

    public static Spawner ofPlatform() {
        return new Spawner(Thread.ofPlatform());
    }

    public static Spawner ofPlatform(String prefix) {
        return new Spawner(Thread.ofPlatform().name(prefix, 0));
    }

    private Spawner(Thread.Builder threadBuilder) {
        executorService = Executors.newThreadPerTaskExecutor(threadBuilder.factory());
    }

    public void spawn(Runnable runnable) {
        executorService.execute(runnable);
    }

    public void close() {
        executorService.close();
    }
}
