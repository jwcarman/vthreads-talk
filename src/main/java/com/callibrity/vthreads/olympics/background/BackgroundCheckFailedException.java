package com.callibrity.vthreads.olympics.background;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Background check failed!")
public class BackgroundCheckFailedException extends RuntimeException {
}
