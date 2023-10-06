package com.example.oauthclient.controller;



import com.example.oauthclient.config.WelcomeClient;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class WelcomeController {

    private final WelcomeClient welcomeClient;

    @GetMapping("/")
    public String welcome(Authentication authentication) {

        String authorities = authentication.getName() + " - " + authentication.getAuthorities().toString();
        String welcome = welcomeClient.getWelcome();
        return "<h1>" +  welcome + "</h1><h2>" + authorities + "</h2>";
    }

}































