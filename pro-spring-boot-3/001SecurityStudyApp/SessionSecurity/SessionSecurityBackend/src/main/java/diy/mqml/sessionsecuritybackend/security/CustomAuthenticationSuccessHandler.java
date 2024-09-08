package diy.mqml.sessionsecuritybackend.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * user should receive header in the response: Set-Cookie: SESSION=<session-id>; Path=/; HttpOnly; SameSite=Lax
 * */

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // Retrieve the current session or create a new one
        HttpSession session = request.getSession();

        // Get the session ID
        String sessionId = session.getId();

        // Set the session ID in the Set-Cookie header
        String cookieHeader = "SESSION=" + sessionId + "; Path=/; HttpOnly; SameSite=Lax";
        response.addHeader(HttpHeaders.SET_COOKIE, cookieHeader);

        // Optionally redirect the user to a specific page
        response.sendRedirect("/home");  // Redirect after successful login
    }
}
