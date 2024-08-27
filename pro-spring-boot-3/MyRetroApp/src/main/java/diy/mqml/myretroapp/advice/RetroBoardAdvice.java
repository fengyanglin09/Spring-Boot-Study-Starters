package diy.mqml.myretroapp.advice;


import diy.mqml.myretroapp.board.RetroBoard;
import diy.mqml.myretroapp.exception.RetroBoardNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@Aspect
public class RetroBoardAdvice {

    /**
     <p>
     @execution: This is the key to the Advice. This keyword needs a pattern matching that identifies the method to be advised. In this case, we are looking for every method with any return type (*) and looking for that specific method in the com.apress.myretro.repository.RetroBoardRepository.findById that has as a parameter the UUID. In this case, this is very straightforward, but we can have an expression like this: * com.apress.*..*.find*(*). This means finding any class that is between the package apress and up and any class that has the find prefix for the method that accepts any number of parameters, no matter the type.
     </p>
     <p>
     @ProceedingJoinPoint: This is an interface, and its implementation knows how to get the object that is being advised
     </p>
     * */

    @Around("execution(* diy.mqml.myretroapp.persistence.RetroBoardRepository.findById(java.util.UUID))")
    public Object checkFindRetroBoard(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("[ADVICE] {}", proceedingJoinPoint.getSignature().getName());
        Optional<RetroBoard> retroBoard = (Optional<RetroBoard>) proceedingJoinPoint.proceed(new Object[]{
                UUID.fromString(proceedingJoinPoint.getArgs()[0].toString())
        });
        if (retroBoard.isEmpty())
            throw new RetroBoardNotFoundException();

        return retroBoard;
    }
}
