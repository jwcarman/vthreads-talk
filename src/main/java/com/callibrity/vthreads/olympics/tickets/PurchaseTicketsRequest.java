package com.callibrity.vthreads.olympics.tickets;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseTicketsRequest(@NotEmpty String firstName, @NotEmpty String lastName,
                                     @NotNull @Positive Integer count) {
}
