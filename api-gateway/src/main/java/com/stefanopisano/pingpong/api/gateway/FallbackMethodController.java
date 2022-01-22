package com.stefanopisano.pingpong.api.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackMethodController {

    @GetMapping("/authServiceFallback")
    public String pingServiceFallbackMethod() {
        return "Authentication Service is down.";
    }

}
