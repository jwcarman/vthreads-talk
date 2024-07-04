package com.callibrity.vthreads.olympics.background.interpol;

import com.callibrity.vthreads.olympics.background.BackgroundCheckFailedException;
import com.callibrity.vthreads.olympics.background.BackgroundCheckService;
import com.callibrity.vthreads.utils.Sleeps;
import org.springframework.stereotype.Service;

@Service
public class InterpolBackgroundCheckService implements BackgroundCheckService {

    @Override
    public void verifyBackground(String lastName, String firstName) {
        Sleeps.sleepMillis(500);
        if("Nefario".equalsIgnoreCase(lastName)) {
            throw new BackgroundCheckFailedException();
        }
    }
}
