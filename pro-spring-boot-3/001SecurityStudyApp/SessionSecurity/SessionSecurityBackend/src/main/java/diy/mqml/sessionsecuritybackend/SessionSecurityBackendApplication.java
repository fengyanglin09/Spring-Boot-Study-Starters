package diy.mqml.sessionsecuritybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
@EnableJdbcHttpSession
public class SessionSecurityBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SessionSecurityBackendApplication.class, args);
    }

}
