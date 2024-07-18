package com.callibrity.vthreads.examples;

import com.callibrity.vthreads.utils.Sleeps;
import com.callibrity.vthreads.utils.Spawner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.callibrity.vthreads.utils.Runnables.forever;
import static com.callibrity.vthreads.utils.Runnables.noop;
import static com.callibrity.vthreads.utils.Runnables.serial;

public class CooperativeScheduling {

    private static final Logger logger = LoggerFactory.getLogger(CooperativeScheduling.class);

    public static void main(String[] args) {
        try (Spawner spawner = Spawner.ofVirtual()) {
            spawner.spawn(forever(CooperativeScheduling::sleepy));
            spawner.spawn(forever(CooperativeScheduling::sleepy));
            spawner.spawn(serial(
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
