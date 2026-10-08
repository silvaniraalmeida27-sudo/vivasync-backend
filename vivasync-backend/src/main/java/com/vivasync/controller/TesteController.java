package com.vivasync.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    @GetMapping("/")
    public String inicio() {
        return "VivaSync Back-End funcionando!";
    }
}