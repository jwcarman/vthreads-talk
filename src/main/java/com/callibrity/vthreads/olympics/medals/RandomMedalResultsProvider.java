package com.callibrity.vthreads.olympics.medals;

import com.callibrity.vthreads.olympics.value.Country;
import com.callibrity.vthreads.olympics.value.Medal;
import com.callibrity.vthreads.olympics.value.Sport;
import com.callibrity.vthreads.utils.Sleeps;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomMedalResultsProvider implements MedalResultsProvider {

    private final Set<MedalResult> medalResults;

    public RandomMedalResultsProvider(Sport... sports) {
        this.medalResults = Arrays.stream(sports).flatMap(sport -> {
            final Medal[] medalClasses = Medal.values();
            final int n = medalClasses.length;
            final List<Country> countries = Country.randomCountries(n);
            return IntStream.range(0, n).mapToObj(i -> new MedalResult(sport, countries.get(i), medalClasses[i]));
        }).collect(Collectors.toSet());
    }

    @Override
    public Set<MedalResult> listAllMedalResults() {
        Sleeps.sleepRandomMillis(100, 300);
        return medalResults;
    }
}
