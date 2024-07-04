package com.callibrity.vthreads.philosophers;

import com.callibrity.vthreads.utils.Sleeps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Philosopher implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Philosopher.class);
    private static final int EAT_TIME_MAX = 500;

    private final Fork leftFork;
    private final Fork rightFork;
    private final String name;

    public Philosopher(Fork leftFork, Fork rightFork, String name) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.name = name;
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
        logger.info("{}: {} thinking until left fork is available...", Thread.currentThread(), name);
        leftFork.pickUp();
        logger.info("{}: {} picked up left fork.", Thread.currentThread(), name);
    }

    private void pickUpRightFork() {
        logger.info("{}: {} thinking until right fork is available...", Thread.currentThread(), name);
        rightFork.pickUp();
        logger.info("{}: {} picked up right fork.", Thread.currentThread(), name);
    }

    private void putDownLeftFork() {
        logger.info("{}: {} putting down left fork...", Thread.currentThread(), name);
        leftFork.putDown();
        logger.info("{}: {} put down left fork.", Thread.currentThread(), name);
    }

    private void putDownRightFork() {
        logger.info("{}: {} putting down right fork...", Thread.currentThread(), name);
        rightFork.putDown();
        logger.info("{}: {} put down right fork.", Thread.currentThread(), name);
    }

    private void eat() {
        logger.info("{}: {} eating...", Thread.currentThread(), name);
        final int eatTime = Sleeps.sleepRandomMillis(EAT_TIME_MAX);
        logger.info("{}: {} finished eating after {} milliseconds.", Thread.currentThread(), name, eatTime);
    }
}
