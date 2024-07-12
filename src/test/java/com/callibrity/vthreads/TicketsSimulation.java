package com.callibrity.vthreads;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.rampUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class TicketsSimulation extends Simulation {

    final HttpProtocolBuilder protocol =
            http.baseUrl("http://192.168.1.180:9080")
                    .acceptHeader("application/json")
                    .contentTypeHeader("application/json");

    final ScenarioBuilder purchaseTickets = scenario("Purchase Tickets")
            .exec(http("Purchase Request")
                    .post("/tickets")
                    .body(StringBody("{\"firstName\":\"James\", \"lastName\":\"Carman\", \"count\": 3}"))
                    .check(status().is(200)));

    {
        setUp(
                purchaseTickets.injectOpen(rampUsersPerSec(100).to(1000).during(300))
        ).protocols(protocol);
    }
}
