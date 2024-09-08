package diy.mqml.sessionsecuritybackend.repository;


import diy.mqml.sessionsecuritybackend.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,String> {
    Optional<User> findByEmail(String email);

}
