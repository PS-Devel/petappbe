package com.ps.petappbe.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping
    public String auth() {

        return "auth";
    }
}