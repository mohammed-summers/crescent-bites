package com.mks.crescentbites.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("monitoring")
public class MonitoringController {

    @GetMapping("/java-version")
    public String getJavaVersion() {
        Runtime.Version runtimeVersion = Runtime.version();
        log.info("Java Version: " + runtimeVersion);
        return "Java Version: " + runtimeVersion;
    }

    @GetMapping("/db-connection")
    public void getDbConnection() {
        log.info("DB Connection Successful");
    }
}
