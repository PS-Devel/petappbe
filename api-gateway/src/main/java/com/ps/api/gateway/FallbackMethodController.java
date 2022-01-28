package com.ps.api.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackMethodController {

    @GetMapping("/users-service-fallback")
    public String usersServiceFallbackMethod() {
        return "Users Service is down.";
    }

}
