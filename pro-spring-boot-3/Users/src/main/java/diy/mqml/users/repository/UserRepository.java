package diy.mqml.users.repository;


import diy.mqml.users.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,String> {
    Optional<User> findByEmail(String email);

}
