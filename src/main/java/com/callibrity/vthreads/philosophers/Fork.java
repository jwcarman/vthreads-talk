package com.callibrity.vthreads.philosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    private final Lock mutex = new ReentrantLock();

    public void pickUp() {
        mutex.lock();
    }

    public void putDown() {
        mutex.unlock();
    }
}
