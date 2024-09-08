package diy.mqml.sessionsecuritybackend.security;



import diy.mqml.sessionsecuritybackend.model.User;
import diy.mqml.sessionsecuritybackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import diy.mqml.sessionsecuritybackend.exception.UserNotFoundException;
import diy.mqml.sessionsecuritybackend.model.UserRole;

@Service
@AllArgsConstructor
public class UserSecurityDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    private PasswordEncoder encoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(UserNotFoundException::new);


        final UserDetails build = org.springframework.security.core.userdetails.User
                .withUsername(username)
                .roles(user.getUserRole().stream().map(UserRole::toString).toArray(String[]::new))
                .password(user.getPassword())
//                .password(encoder.encode(user.getPassword()))
                .accountExpired(!user.isActive())
                .build();

        return build;
    }
}
