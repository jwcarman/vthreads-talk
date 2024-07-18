package com.callibrity.vthreads.olympics.medals;

import com.callibrity.vthreads.olympics.value.Sport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedalsConfig {

    @Bean
    public MedalResultsProvider stadeDeFranceMedalProvider() {
        return new RandomMedalResultsProvider(Sport.ATHLETICS, Sport.RUGBY_SEVENS);
    }

    @Bean
    public MedalResultsProvider rolandGarrosStadium() {
        return new RandomMedalResultsProvider(Sport.TENNIS, Sport.BOXING);
    }

    @Bean
    public MedalResultsProvider parcDesPrinces() {
        return new RandomMedalResultsProvider(Sport.FOOTBALL);
    }

    @Bean
    public MedalResultsProvider placeDeLaConcorde() {
        return new RandomMedalResultsProvider(Sport.CYCLING_BMX_FREESTYLE, Sport.SKATEBOARDING, Sport.BREAKDANCING, Sport.BASKETBALL);
    }

    @Bean
    public MedalResultsProvider grandPalais() {
        return new RandomMedalResultsProvider(Sport.FENCING, Sport.TAEKWONDO);
    }

    @Bean
    public MedalResultsProvider eiffelTower() {
        return new RandomMedalResultsProvider(Sport.BEACH_VOLLEYBALL, Sport.JUDO, Sport.WRESTLING);
    }

    @Bean
    public MedalResultsProvider esplanadeDesInvalides() {
        return new RandomMedalResultsProvider(Sport.ARCHERY);
    }

    @Bean
    public MedalResultsProvider chateauDeVersailles() {
        return new RandomMedalResultsProvider(Sport.EQUESTRIAN, Sport.MODERN_PENTATHLON);
    }

    @Bean
    public MedalResultsProvider teahupoo() {
        return new RandomMedalResultsProvider(Sport.SURFING);
    }

    @Bean
    public MedalResultsProvider yvesDuManoir() {
        return new RandomMedalResultsProvider(Sport.HOCKEY);
    }
}
