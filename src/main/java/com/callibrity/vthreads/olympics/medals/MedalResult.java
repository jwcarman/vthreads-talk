package com.callibrity.vthreads.olympics.medals;

import com.callibrity.vthreads.olympics.value.Country;
import com.callibrity.vthreads.olympics.value.Medal;
import com.callibrity.vthreads.olympics.value.Sport;

public record MedalResult(Sport sport, Country country, Medal medal) {
}
