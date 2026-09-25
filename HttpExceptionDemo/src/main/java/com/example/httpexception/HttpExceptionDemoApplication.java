package com.example.httpexception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.httpexception.exception.*;

@SpringBootApplication
@RestController
@RequestMapping("/api/test")
public class HttpExceptionDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpExceptionDemoApplication.class, args);
    }

    // 200 OK
    @GetMapping("/200")
    public Map<String, Object> test200() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 200);
        response.put("message", "Request completed successfully!");
        return response;
    }

    // 400 Bad Request
    @GetMapping("/400")
    public void test400() {
        throw new BadRequestException("Invalid request parameter provided.");
    }

    // 401 Unauthorized
    @GetMapping("/401")
    public void test401() {
        throw new UnauthorizedException("Authentication token is missing or invalid.");
    }

    // 403 Forbidden
    @GetMapping("/403")
    public void test403() {
        throw new ForbiddenException("You do not have permission to access this resource.");
    }

    // 404 Not Found
    @GetMapping("/404")
    public void test404() {
        throw new ResourceNotFoundException("Requested user account was not found.");
    }

    // 409 Conflict
    @GetMapping("/409")
    public void test409() {
        throw new DataConflictException("A record with this email already exists.");
    }

    // 500 Internal Server Error
    @GetMapping("/500")
    public void test500() {
        throw new RuntimeException("Unexpected internal server processing failure.");
    }
}
