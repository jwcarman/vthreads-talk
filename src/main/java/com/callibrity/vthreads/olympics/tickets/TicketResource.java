package com.callibrity.vthreads.olympics.tickets;

import com.callibrity.vthreads.olympics.background.BackgroundCheckService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/tickets")
@Validated
public class TicketResource {

    private final BackgroundCheckService backgroundCheckService;


    public TicketResource(BackgroundCheckService backgroundCheckService) {
        this.backgroundCheckService = backgroundCheckService;
    }

    @PostMapping
    PurchaseTicketsResponse purchaseTickets(@RequestBody @NotNull @Valid PurchaseTicketsRequest request) {
        backgroundCheckService.verifyBackground(request.lastName(), request.firstName());
        final List<Ticket> tickets = IntStream.range(0, request.count())
                .mapToObj(i -> new Ticket(UUID.randomUUID().toString(), request.lastName(), request.firstName()))
                .toList();

        return new PurchaseTicketsResponse(tickets);
    }
}
