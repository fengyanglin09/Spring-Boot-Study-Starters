package diy.mqml.sessionsecuritybackend.web;



import diy.mqml.sessionsecuritybackend.model.User;
import diy.mqml.sessionsecuritybackend.model.UserGravatar;
import diy.mqml.sessionsecuritybackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersController {

    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<Iterable<User>> getAll(){
        return ResponseEntity.ok(this.userRepository.findAll());
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> findUserById(@PathVariable String email) throws Throwable {
        return ResponseEntity.of(this.userRepository.findById(email));
    }

    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT})
    public ResponseEntity<User> save(@RequestBody User user){
       this.userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{email}")
                .buildAndExpand(user.getEmail())
                .toUri();
        return ResponseEntity.created(location).body(user);
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable String email){
        this.userRepository.deleteById(email);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    @GetMapping("/{email}/gravatar")
    public ResponseEntity<String> getUserGravatar(@PathVariable String email) throws Throwable {
        return ResponseEntity.ok(UserGravatar.getGravatarUrlFromEmail(email));
    }
}

