package com.callibrity.vthreads.philosophers;

import com.callibrity.vthreads.utils.Spawner;

import static com.callibrity.vthreads.utils.Runnables.repeat;

public class DiningPhilosophers {
    public static void main(String[] args) {

        final String[] philosophers = new String[]{"Plato", "Aristotle", "Socrates", "Confucius", "Descartes"};
        final int nPhilosophers = philosophers.length;
        final int nBites = 5;
        final Fork[] forks = new Fork[nPhilosophers];
        for (int i = 0; i < nPhilosophers; i++) {
            forks[i] = new Fork();
        }

        try(Spawner spawner = Spawner.ofVirtual()) {
            for (int i = 0; i < nPhilosophers; i++) {
                final int minIndex = Math.min(i, (i + 1) % nPhilosophers);
                final int maxIndex = Math.max(i, (i + 1) % nPhilosophers);
                spawner.spawn(philosophers[i], repeat(nBites, new Philosopher(forks[minIndex], forks[maxIndex])));
            }
        }
    }
}
