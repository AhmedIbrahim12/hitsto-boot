package com.hitsto.application.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private final Logger LOGGER = LogManager.getLogger(IndexController.class);

    @GetMapping("/")
    public String displayMessage() {

        LOGGER.debug("Display message");
        return "Welcome to Hitsto";
    }
}
