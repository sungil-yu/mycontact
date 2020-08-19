package com.project3.mycontact.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldController {

    @GetMapping("/api/hello")
    public String helloWorld(){

        return "Hi";
    }
}
