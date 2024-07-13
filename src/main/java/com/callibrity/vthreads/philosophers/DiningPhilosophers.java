package com.callibrity.vthreads.philosophers;

import com.callibrity.vthreads.utils.Loggers;
import com.callibrity.vthreads.utils.Spawner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.callibrity.vthreads.utils.Runnables.repeat;
import static com.callibrity.vthreads.utils.SystemProperties.intProperty;
import static com.callibrity.vthreads.utils.SystemProperties.longProperty;
import static com.callibrity.vthreads.utils.SystemProperties.stringProperty;

public class DiningPhilosophers {

    private static final Logger logger = LoggerFactory.getLogger(DiningPhilosophers.class);

    public static void main(String[] args) {
        final String log = stringProperty("log", "info");
        final int nPhilosophers = intProperty("philosophers", 5);
        final int nBites = intProperty("bites", 5);
        final long eatTimeInMillis = longProperty("eatTimeInMillis", 50);

        final Loggers.Function loggerFn = Loggers.byName(DiningPhilosophers.class, log);

        final Fork[] forks = new Fork[nPhilosophers];
        for (int i = 0; i < nPhilosophers; i++) {
            forks[i] = new Fork();
        }
        final long before = System.nanoTime();
        try (Spawner spawner = Spawner.ofVirtual("philosopher-") ) {
            logger.info("Spawning {} philosophers...", nPhilosophers);
            for (int i = 0; i < nPhilosophers; i++) {
                // Using the "resource hierarchy solution" (aka one left-handed philosopher) to avoid deadlock...
                final int minIndex = Math.min(i, (i + 1) % nPhilosophers);
                final int maxIndex = Math.max(i, (i + 1) % nPhilosophers);
                spawner.spawn(repeat(nBites, new Philosopher(forks[minIndex], forks[maxIndex], eatTimeInMillis, loggerFn)));
            }
            logger.info("Finished spawning {} philosophers.", nPhilosophers);
        }
        final long after = System.nanoTime();
        logger.info("Finished feeding {} philosophers {} bites in {} seconds.", nPhilosophers, nBites, (after - before) / 1_000_000_000.0);
    }
}
