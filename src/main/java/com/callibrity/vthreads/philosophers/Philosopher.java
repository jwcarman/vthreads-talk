package com.callibrity.vthreads.philosophers;

import com.callibrity.vthreads.utils.Sleeps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Philosopher implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Philosopher.class);

    private final Fork leftFork;
    private final Fork rightFork;
    private final long eatTimeInMillis;

    public Philosopher(Fork leftFork, Fork rightFork, long eatTimeInMillis) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.eatTimeInMillis = eatTimeInMillis;
    }

    @Override
    public void run() {
        pickUpLeftFork();
        pickUpRightFork();
        eat();
        putDownRightFork();
        putDownLeftFork();
    }

    private void pickUpLeftFork() {
        logger.info("{}: thinking until left fork is available...", Thread.currentThread());
        leftFork.pickUp();
        logger.info("{}: picked up left fork.", Thread.currentThread());
    }

    private void pickUpRightFork() {
        logger.info("{}: thinking until right fork is available...", Thread.currentThread());
        rightFork.pickUp();
        logger.info("{}: picked up right fork.", Thread.currentThread());
    }

    private void putDownLeftFork() {
        logger.info("{}: putting down left fork...", Thread.currentThread());
        leftFork.putDown();
        logger.info("{}: put down left fork.", Thread.currentThread());
    }

    private void putDownRightFork() {
        logger.info("{}: putting down right fork...", Thread.currentThread());
        rightFork.putDown();
        logger.info("{}: put down right fork.", Thread.currentThread());
    }

    private void eat() {
        logger.info("{}: eating...", Thread.currentThread());
        Sleeps.sleepMillis(eatTimeInMillis);
        logger.info("{}: finished eating after {} milliseconds.", Thread.currentThread(), eatTimeInMillis);
    }
}
