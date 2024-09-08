package diy.mqml.sessionsecuritybackend.config;



import diy.mqml.sessionsecuritybackend.model.User;
import diy.mqml.sessionsecuritybackend.model.UserRole;
import diy.mqml.sessionsecuritybackend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@Configuration
public class UserConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {

        return new PlainTextPasswordEncoder();
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository){
        return args -> {
            userRepository.save(new User("ximena@email.com","Ximena","https://www.gravatar.com/avatar/23bb62a7d0ca63c9a804908e57bf6bd4?d=wavatar", "aw2s0meR!", List.of(UserRole.USER),true));
            userRepository.save(new User("norma@email.com","Norma" ,"https://www.gravatar.com/avatar/f07f7e553264c9710105edebe6c465e7?d=wavatar", "aw2s0meR!", List.of(UserRole.ADMIN),true));
            userRepository.save(new User("manager@email.com","Manager" ,"https://www.gravatar.com/avatar/f07f7e553264c9710102edebe6c465e7?d=wavatar", "aw2s0meR!", List.of(UserRole.ADMIN),true));
        };
    }

}
