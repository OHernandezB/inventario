package com.inventario.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "API funcionando correctamente!";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
} 