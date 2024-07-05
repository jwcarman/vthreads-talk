package com.callibrity.vthreads.olympics.background.interpol;

import com.callibrity.vthreads.olympics.background.BackgroundCheckService;
import com.callibrity.vthreads.utils.Sleeps;
import org.springframework.stereotype.Service;

@Service
public class InterpolBackgroundCheckService implements BackgroundCheckService {

    @Override
    public void verifyBackground(String lastName, String firstName) {
        // Mimic some long-running operation (like calling a web service)...
        Sleeps.sleepMillis(500);
    }
}
