package com.callibrity.vthreads.examples;

import com.callibrity.vthreads.utils.Sleeps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.ThreadFactory;
import java.util.function.Supplier;

public class StructuredConcurrency {
    private static final Logger logger = LoggerFactory.getLogger(StructuredConcurrency.class);

    public static void main(String[] args) {
        logger.info("{}", fetchViewModel("12345"));
    }


    private static BankingAppViewModel fetchViewModel(final String customerId) {
        final ThreadFactory factory = Thread.ofVirtual().factory();
        try (var scope = new StructuredTaskScope.ShutdownOnFailure("fetchViewModel", factory)) {

            Supplier<Customer> customer = scope.fork(() -> fetchCustomer(customerId));
            Supplier<List<Account>> accounts = scope.fork(() -> fetchAccounts(customerId));

            scope.join().throwIfFailed(t -> new BankingAppViewException(customerId));

            return new BankingAppViewModel(customer.get(), accounts.get());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new BankingAppViewException(customerId);
        }
    }

    private static List<Account> fetchAccounts(String customerId) {
        Sleeps.sleepMillis(3000);
        return List.of(
                new Account("12345", AccountType.CHECKING, BigDecimal.valueOf(1000)),
                new Account("67890", AccountType.SAVINGS, BigDecimal.valueOf(5000))
        );
    }

    private static Customer fetchCustomer(String customerId) {
        return new Customer(customerId, "John", "Doe");
    }


    private record Customer(String customerId, String firstName, String lastName) {

    }

    private record Account(String accountNumber, AccountType type, BigDecimal balance) {

    }

    private record BankingAppViewModel(Customer customer, List<Account> accounts) {

    }

    private enum AccountType {
        CHECKING,
        SAVINGS,
        CD
    }


    private static class BankingAppViewException extends RuntimeException {
        private BankingAppViewException(String customerId) {
            super("Failed to fetch banking app view for customer " + customerId);
        }
    }
}
