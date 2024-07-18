package com.callibrity.vthreads.examples;

import java.util.concurrent.Semaphore;

public class SemaphoreConcurrencyLimiting {

    private final CreditScoreService creditScoreService;
    private final Semaphore semaphore;

    public SemaphoreConcurrencyLimiting(CreditScoreService creditScoreService, int concurrency) {
        this.creditScoreService = creditScoreService;
        this.semaphore = new Semaphore(concurrency);
    }

    public Account openAccount(Person person) {
        semaphore.acquireUninterruptibly();
        try {
            if (creditScoreService.creditScore(person.ssn()) < 700) {
                throw new IllegalStateException("Credit score too low");
            }
            return new Account("12345", person);
        } finally {
            semaphore.release();
        }
    }

    // Types for illustrative purposes only...
    private interface CreditScoreService {
        int creditScore(String ssn);
    }

    public record Person(String firstName, String lastName, String ssn) {

    }

    public record Account(String accountNumber, Person owner) {

    }
}
