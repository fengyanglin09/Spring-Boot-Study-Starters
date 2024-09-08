package diy.mqml.sessionsecuritybackend.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "Please provide your login details.";
    }

    @GetMapping("/success")
    public String success() {
        return "Login successful!";
    }

    @GetMapping("/logout")
    public String logout() {
        return "You have been logged out.";
    }
}
