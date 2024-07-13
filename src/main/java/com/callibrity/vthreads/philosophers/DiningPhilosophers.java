package com.callibrity.vthreads.philosophers;

import com.callibrity.vthreads.utils.Spawner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.callibrity.vthreads.utils.Runnables.repeat;

public class DiningPhilosophers {

    private static final Logger logger = LoggerFactory.getLogger(DiningPhilosophers.class);

    public static void main(String[] args) {

        final int nPhilosophers = 5;
        final int nBites = 5;
        final Fork[] forks = new Fork[nPhilosophers];
        for (int i = 0; i < nPhilosophers; i++) {
            forks[i] = new Fork();
        }
        final long before = System.nanoTime();
        try (Spawner spawner = Spawner.ofVirtual("philosopher-") ) {
            logger.info("Spawning all philosophers...");
            for (int i = 0; i < nPhilosophers; i++) {
                final int minIndex = Math.min(i, (i + 1) % nPhilosophers);
                final int maxIndex = Math.max(i, (i + 1) % nPhilosophers);
                spawner.spawn(repeat(nBites, new Philosopher(forks[minIndex], forks[maxIndex], 500)));
            }
            logger.info("Finished spawning all philosophers.");
        }
        final long after = System.nanoTime();
        logger.info("Finished feeding {} philosophers in {} seconds.", nPhilosophers, (after - before) / 1_000_000_000.0);
    }
}
