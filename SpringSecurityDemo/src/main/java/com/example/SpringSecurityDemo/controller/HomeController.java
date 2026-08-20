package com.example.SpringSecurityDemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // Public Welcome Endpoint
    @GetMapping({"/", "/welcome"})
    public String welcome() {
        return "Welcome to Spring Security";
    }

    // Secured User Endpoint
    @GetMapping("/user")
    public String welcomeUser() {
        return "Welcome User";
    }

    // Secured Admin Endpoint
    @GetMapping("/admin")
    public String welcomeAdmin() {
        return "Welcome Admin";
    }
}
