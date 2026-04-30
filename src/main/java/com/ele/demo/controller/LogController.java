package com.ele.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Log Controller")
@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @Operation(summary = "Trigger all log levels")
    @GetMapping("/all")
    public ResponseEntity<String> allLevels() {
        log.trace("TRACE level message");
        log.debug("DEBUG level message");
        log.info("INFO level message");
        log.warn("WARN level message");
        log.error("ERROR level message");
        return ResponseEntity.ok("All log levels triggered — check console and ./target/log/");
    }

    @Operation(summary = "Trigger ERROR with exception")
    @GetMapping("/error")
    public ResponseEntity<String> errorWithException() {
        try {
            throw new RuntimeException("Simulated exception for log test");
        } catch (RuntimeException e) {
            log.error("Caught an exception during processing", e);
        }
        return ResponseEntity.ok("Error log with exception triggered");
    }
}
