package diy.mqml.sessionsecuritybackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
public class SessionConfig {

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("SESSIONID");  // Custom cookie name
        serializer.setCookiePath("/");          // Define the cookie path
        serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");  // Optional: Set domain pattern
        serializer.setUseHttpOnlyCookie(true);  // HttpOnly for security
        serializer.setSameSite("Lax");          // SameSite attribute
        serializer.setUseSecureCookie(true);    // Secure cookie for HTTPS
        serializer.setCookieMaxAge(30*60);
        return serializer;
    }
}
