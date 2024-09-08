package diy.mqml.sessionsecuritybackend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        // Set response status to 401 (Unauthorized)
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Create a response map
        Map<String, String> data = new HashMap<>();
        data.put("error", "Authentication failed");
        data.put("message", exception.getMessage());

        // Set response content type and write JSON output
        response.setContentType("application/json");

//        response.sendRedirect("/login?error=true");

        response.getWriter().write(new ObjectMapper().writeValueAsString(data));
    }
}
