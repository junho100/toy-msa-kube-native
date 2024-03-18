package com.example.userservice.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check")
public class HealthCheckController {
    @RequestMapping()
    public String status() {
        return "Test4";
    }
}
