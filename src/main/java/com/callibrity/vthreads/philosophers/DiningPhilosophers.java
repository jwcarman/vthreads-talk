package com.callibrity.vthreads.philosophers;

import com.callibrity.vthreads.utils.ThreadSpawner;

import static com.callibrity.vthreads.utils.Runnables.repeat;

public class DiningPhilosophers {
    public static void main(String[] args) {

        final int nPhilosophers = 5;
        final int nBites = 1;

        final Fork[] forks = new Fork[nPhilosophers];
        for (int i = 0; i < nPhilosophers; i++) {
            forks[i] = new Fork();
        }

        try(ThreadSpawner spawner = ThreadSpawner.ofVirtual()) {
            for (int i = 0; i < nPhilosophers; i++) {
                final int minIndex = Math.min(i, (i + 1) % nPhilosophers);
                final int maxIndex = Math.max(i, (i + 1) % nPhilosophers);
                spawner.spawn(repeat(nBites, new Philosopher(forks[minIndex], forks[maxIndex], "Philosopher " + i)));
            }
        }
    }
}
