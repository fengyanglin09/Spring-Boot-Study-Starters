package diy.mqml.sessionsecuritybackend.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @GetMapping("/session-info")
    public String getSessionInfo(HttpSession session) {
        return "Session ID: " + session.getId();
    }
}
