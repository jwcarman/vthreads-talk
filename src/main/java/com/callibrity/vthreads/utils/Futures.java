package com.callibrity.vthreads.utils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Supplier;

public class Futures {

    private Futures() {
        // Prevent instantiation!
    }

    public static <T> T get(Future<T> future, Supplier<T> defaultSupplier) {
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return defaultSupplier.get();
        } catch (ExecutionException e) {
            return defaultSupplier.get();
        }
    }
}
