package com.callibrity.vthreads.examples;

import com.callibrity.vthreads.utils.Sleeps;
import com.callibrity.vthreads.utils.Spawner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.callibrity.vthreads.utils.Runnables.forever;
import static com.callibrity.vthreads.utils.Runnables.noop;
import static com.callibrity.vthreads.utils.Runnables.serial;

public class NonBlocking {

    private static final Logger logger = LoggerFactory.getLogger(NonBlocking.class);

    public static void main(String[] args) {
        try (Spawner spawner = Spawner.ofVirtual()) {
            spawner.spawn("alice", forever(NonBlocking::sleepy));
            spawner.spawn("bob", forever(NonBlocking::sleepy));
            spawner.spawn("chad", serial(
                            () -> logger.info("{}: entering an infinite loop...", Thread.currentThread()),
                            forever(noop()))
            );
        }
    }

    private static void sleepy() {
        logger.info("{}: sleeping for 1 second...", Thread.currentThread());
        Sleeps.sleepMillis(1000);
        logger.info("{}: finished sleeping.", Thread.currentThread());
    }
}
