package com.callibrity.vthreads.olympics.medals;

import com.callibrity.vthreads.olympics.value.Country;

public record MedalStandings(Country country, int gold, int silver, int bronze, int total) {

    public static MedalStandings blank(Country country) {
        return new MedalStandings(country, 0, 0, 0, 0);
    }

    public MedalStandings add(MedalResult result) {
        return switch (result.medal()) {
            case GOLD -> new MedalStandings(country, gold + 1, silver, bronze, total + 1);
            case SILVER -> new MedalStandings(country, gold, silver + 1, bronze, total + 1);
            case BRONZE -> new MedalStandings(country, gold, silver, bronze + 1, total + 1);
        };
    }
}
