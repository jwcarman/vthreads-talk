package com.callibrity.vthreads.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreatingWithExecutorService {

    public static void main(String[] args) {
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 100; ++i) {
                executorService.submit(() -> System.out.println("Hello, World!"));
            }
        }
        System.out.println("When will this print?");
    }
}
