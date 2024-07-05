package com.callibrity.vthreads.utils;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class Sleeps {

    private static final SecureRandom rand = new SecureRandom();

    private Sleeps() {

    }

    public static int sleepRandomMillis(int min, int max) {
        final int sleepTime = min + rand.nextInt(max - min);
        sleepMillis(sleepTime);
        return sleepTime;
    }

    public static int sleepRandomMillis(int max) {
        return sleepRandomMillis(0, max);
    }

    public static void sleep(long amount, TemporalUnit unit) {
        try {
            Thread.sleep(Duration.of(amount, unit));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void sleepMillis(long millis) {
        sleep(millis, ChronoUnit.MILLIS);
    }
}
