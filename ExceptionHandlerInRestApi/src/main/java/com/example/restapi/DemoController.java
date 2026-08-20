package com.example.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/divide")
    public int divide() {
        int result = 10 / 0;   // This throws ArithmeticException
        return result;
    }
}