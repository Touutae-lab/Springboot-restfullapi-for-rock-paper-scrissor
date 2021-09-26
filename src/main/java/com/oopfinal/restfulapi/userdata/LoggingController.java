package com.oopfinal.restfulapi.userdata;

import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class LoggingController {

    private static final Logger logger = LoggerFactory.getLogger(LoggingController.class);
    public static void log(String message) {
        logger.info(message);
    }
}
