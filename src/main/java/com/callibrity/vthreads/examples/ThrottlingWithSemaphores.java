package com.callibrity.vthreads.examples;

import com.callibrity.vthreads.utils.Sleeps;
import com.callibrity.vthreads.utils.Spawner;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;

import static com.callibrity.vthreads.utils.Runnables.withSemaphore;

public class ThrottlingWithSemaphores {

    private static final Logger logger = LoggerFactory.getLogger(ThrottlingWithSemaphores.class);

    public static void main(String[] args) {
        final int nTasks = 1000;
        final int nPermits = 10;

        final Semaphore semaphore = new Semaphore(nPermits, true);
        logger.info("Spawning {} semaphored tasks...", nTasks);
        final StopWatch stopWatch = StopWatch.createStarted();
        try (Spawner spawner = Spawner.ofVirtual()) {
            for (int i = 0; i < nTasks; ++i) {
                spawner.spawn(withSemaphore(semaphore, ThrottlingWithSemaphores::callRemoteService));
            }
        }
        stopWatch.stop();
        final double seconds = stopWatch.getNanoTime() / 1_000_000_000.0;
        logger.info("Finished {} semaphored tasks in {} seconds at a rate of {}/sec.", nTasks, seconds, nTasks / seconds);
    }

    public static String callRemoteService() {
        Sleeps.sleepMillis(100);
        return RandomStringUtils.randomAlphabetic(10);
    }
}
