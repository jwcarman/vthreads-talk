package com.callibrity.vthreads.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

public class UsingScopedValues {

    private static final Logger logger = LoggerFactory.getLogger(UsingScopedValues.class);

    private static final ScopedValue<String> ACCOUNT_NUMBER = ScopedValue.newInstance();

    public static void main(String[] args) {
        ScopedValue.runWhere(ACCOUNT_NUMBER, "123456789", () -> {
            try(var scope = new StructuredTaskScope.ShutdownOnFailure()) {
                scope.fork(UsingScopedValues::reconcileAccount);
                scope.fork(UsingScopedValues::checkAccountForFraud);
                scope.fork(UsingScopedValues::settleAccount);
                scope.join().throwIfFailed();
                logger.info("Account {} successfully reconciled, checked for fraud, and settled", ACCOUNT_NUMBER.get());
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Interrupted while processing account {}", ACCOUNT_NUMBER.get());
            } catch(ExecutionException e) {
                logger.error("Failed to process account {}", ACCOUNT_NUMBER.get(), e);
            }
        });
    }

    private static boolean reconcileAccount() {
        logger.info("Reconciling account: {}", ACCOUNT_NUMBER.get());
        return true;
    }

    private static boolean checkAccountForFraud() {
        logger.info("Checking account for fraud: {}", ACCOUNT_NUMBER.get());
        return true;
    }

    private static boolean settleAccount() {
        logger.info("Settling account: {}", ACCOUNT_NUMBER.get());
        return true;
    }
}
