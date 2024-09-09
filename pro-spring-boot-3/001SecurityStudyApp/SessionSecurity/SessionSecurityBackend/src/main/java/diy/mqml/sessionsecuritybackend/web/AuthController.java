package diy.mqml.sessionsecuritybackend.web;


import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The Spring Boot backend needs an endpoint that the frontend can hit to refresh the session. This can be a simple endpoint that just checks if the session is still valid, and it will automatically extend the session due to activity.
 * */


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Process the username and password
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        return "Login successful!";
    }

    @GetMapping("/success")
    public String success() {
        return "Login successful!";
    }

    @GetMapping("/logout")
    public String logout() {
        return "You have been logged out.";
    }


    /**
     * The Spring Boot backend needs an endpoint that the frontend can hit to refresh the session. This can be a simple endpoint that just checks if the session is still valid, and it will automatically extend the session due to activity.
     * */
    @GetMapping("/isAuthenticated")
    public Map<String, Object> isAuthenticated(HttpSession session, Authentication authentication) {
        Map<String, Object> response = new HashMap<>();

        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
            response.put("status", "authenticated");
            response.put("username", authentication.getName());
        } else {
            response.put("status", "unauthenticated");
        }
        return response;
    }

}
