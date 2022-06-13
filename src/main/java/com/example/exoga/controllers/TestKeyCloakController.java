package com.example.exoga.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestKeyCloakController {

    @GetMapping("/")
    public String getKeyCloak(){
        return "<h1>index</h1>";
    }

}
