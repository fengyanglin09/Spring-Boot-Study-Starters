package diy.mqml.sessionsecuritybackend.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    @GetMapping("/session-info")
    public String getSessionInfo(HttpSession session) {
        return "Session ID: " + session.getId();
    }

    @GetMapping("/invalidate-session")
    public String invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();  // Invalidate the current session
            return "Session invalidated successfully!";
        }
        return "No session found!";
    }
}
