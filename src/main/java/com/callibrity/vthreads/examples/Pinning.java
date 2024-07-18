package com.callibrity.vthreads.examples;

import com.callibrity.vthreads.utils.Runnables;
import com.callibrity.vthreads.utils.Spawner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.callibrity.vthreads.utils.Runnables.postDelay;
import static com.callibrity.vthreads.utils.Runnables.repeat;

public class Pinning {

    private static final Logger logger = LoggerFactory.getLogger(Pinning.class);

    public static void main(String[] args) {
        final Runnable inner = postDelay(1000, () -> logger.info("{}: Time to go to sleep...", Thread.currentThread()));

        try (Spawner spawner = Spawner.ofVirtual()) {
            spawner.spawn(repeat(10, Runnables.withLock(postDelay(1000, () -> logger.info("{}: with lock...", Thread.currentThread())))));
            spawner.spawn(repeat(10, Runnables.withMonitor(postDelay(1000, () -> logger.info("{}: with monitor...", Thread.currentThread())))));
        }
    }

}
