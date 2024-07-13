package com.callibrity.vthreads.philosophers;

import com.callibrity.vthreads.utils.Loggers;
import com.callibrity.vthreads.utils.Sleeps;

public class Philosopher implements Runnable {

    private final Fork leftFork;
    private final Fork rightFork;
    private final long eatTimeInMillis;
    private final Loggers.Function loggerFn;
    
    public Philosopher(Fork leftFork, Fork rightFork, long eatTimeInMillis, Loggers.Function loggerFn) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.eatTimeInMillis = eatTimeInMillis;
        this.loggerFn = loggerFn;
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
        loggerFn.log("{}: thinking until left fork is available...", Thread.currentThread());
        leftFork.pickUp();
        loggerFn.log("{}: picked up left fork.", Thread.currentThread());
    }

    private void pickUpRightFork() {
        loggerFn.log("{}: thinking until right fork is available...", Thread.currentThread());
        rightFork.pickUp();
        loggerFn.log("{}: picked up right fork.", Thread.currentThread());
    }

    private void putDownLeftFork() {
        loggerFn.log("{}: putting down left fork...", Thread.currentThread());
        leftFork.putDown();
        loggerFn.log("{}: put down left fork.", Thread.currentThread());
    }

    private void putDownRightFork() {
        loggerFn.log("{}: putting down right fork...", Thread.currentThread());
        rightFork.putDown();
        loggerFn.log("{}: put down right fork.", Thread.currentThread());
    }

    private void eat() {
        loggerFn.log("{}: eating...", Thread.currentThread());
        Sleeps.sleepMillis(eatTimeInMillis);
        loggerFn.log("{}: finished eating after {} milliseconds.", Thread.currentThread(), eatTimeInMillis);
    }
}
