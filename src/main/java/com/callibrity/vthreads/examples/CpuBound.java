package com.callibrity.vthreads.examples;

import com.callibrity.vthreads.utils.Sleeps;
import com.callibrity.vthreads.utils.ThreadSpawner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.callibrity.vthreads.utils.Runnables.forever;
import static com.callibrity.vthreads.utils.Runnables.noop;
import static com.callibrity.vthreads.utils.Runnables.serial;

public class CpuBound {

    private static final Logger logger = LoggerFactory.getLogger(CpuBound.class);

    public static void main(String[] args) {
        try(ThreadSpawner spawner = ThreadSpawner.ofVirtual()) {
            spawner.spawn(forever(() -> sleepy("Alice")));
            spawner.spawn(forever(() -> sleepy("Bob")));
            spawner.spawn(serial(() -> logger.info("{}: Chad is going to steal the thread and not give it back...", Thread.currentThread()), forever(noop())));
        }
    }

    private static void sleepy(String name) {
        logger.info("{}: {} sleeping for 1 second...", Thread.currentThread(), name);
        Sleeps.sleepMillis(1000);
        logger.info("{}: {} finished sleeping.", Thread.currentThread(), name);
    }
}
