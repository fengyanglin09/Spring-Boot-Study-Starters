package diy.mqml.myretroapp.web;


import diy.mqml.myretroapp.board.Card;
import diy.mqml.myretroapp.board.RetroBoard;
import diy.mqml.myretroapp.service.RetroBoardService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @GetMapping: This annotation marks several methods that will respond with the HTTP GET method to the /retros endpoint. This is a shortcut of @RequestMapping(method = RequestMethod.GET), which has more parameters that you can use. If you look at the findRetroBoardById method, you will see that @GetMapping is using the value "/{uuid}"; this is a path variable that is mapped to URL patterns. In this case, is using the PathPattern that allows to use matching patterns such as the following:
 *
 <li>
 * "/retros/docu?ent.doc": Match only one character in a path.
</li>
<li>
 * "/retros/*.jpg": Match zero or more characters in a path.
</li>
 * "/retros/**": Match multiple path segments.
<li>
 * "/retros/{project}/versions": Match a path segment and capture it as a variable.
</li>
 * "/retros/{project:[a-z]+}/versions": Match and capture a variable with a regex.
<li>
 * You can have something like this in your method: @GetMapping("/{product:[a-z-]+}-{version:\\d\\.\\d\\.\\d}{ext:\\.[a-z]+}"). As you can see, you have options to declare how you want to access your endpoint, and this applies for every @RequestMapping and its shortcuts.
</li>
 * */

@AllArgsConstructor
@RestController
@RequestMapping("/retros")
public class RetroBoardController {

    private RetroBoardService retroBoardService;

    @GetMapping
    public ResponseEntity<Iterable<RetroBoard>> getAllRetroBoards(){
        return ResponseEntity.ok(retroBoardService.findAll());
    }

    @PostMapping
    public ResponseEntity<RetroBoard> saveRetroBoard(@Valid @RequestBody RetroBoard retroBoard){
        RetroBoard result = retroBoardService.save(retroBoard);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(result.getId().toString())
                .toUri();
        return ResponseEntity.created(location).body(result);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<RetroBoard> findRetroBoardById(@PathVariable UUID uuid){
        return ResponseEntity.ok(retroBoardService.findById(uuid));
    }

    @GetMapping("/{uuid}/cards")
    public ResponseEntity<Iterable<Card>> getAllCardsFromBoard(@PathVariable UUID uuid){
        return ResponseEntity.ok(retroBoardService.findAllCardsFromRetroBoard(uuid));
    }

    @PutMapping("/{uuid}/cards")
    public ResponseEntity<Card> addCardToRetroBoard(@PathVariable UUID uuid,@Valid @RequestBody Card card){
        Card result = retroBoardService.addCardToRetroBoard(uuid,card);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{uuid}/cards/{uuidCard}")
                .buildAndExpand(uuid.toString(),result.getId().toString())
                .toUri();
        return ResponseEntity.created(location).body(result);
    }

    @GetMapping("/{uuid}/cards/{uuidCard}")
    public ResponseEntity<Card> getCardFromRetroBoard(@PathVariable UUID uuid,@PathVariable UUID uuidCard){
        return ResponseEntity.ok(retroBoardService.findCardByUUIDFromRetroBoard(uuid,uuidCard));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}/cards/{uuidCard}")
    public void deleteCardFromRetroBoard(@PathVariable UUID uuid,@PathVariable UUID uuidCard){
        retroBoardService.removeCardFromRetroBoard(uuid,uuidCard);
    }

    /**
     *
     * @ExceptionHandler: This annotation is used to define methods that handle specific exceptions thrown during the execution of controller methods
     * */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();

        response.put("msg","There is an error");
        response.put("code",HttpStatus.BAD_REQUEST.value());
        response.put("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        response.put("errors",errors);

        return response;
    }
}

