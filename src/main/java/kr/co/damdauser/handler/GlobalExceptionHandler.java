package kr.co.damdauser.handler;

import kr.co.error.exception.BusinessLogicException;
import kr.co.error.exception.UserDefineException;
import kr.co.error.model.ErrorResponse;
import kr.co.error.model.ResponseFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {BusinessLogicException.class, RuntimeException.class})
    public ResponseEntity handlerRuntimeException(RuntimeException e){
        ResponseFormat responseFormat = ResponseFormat.fail(e.getMessage());
        return new ResponseEntity(responseFormat, HttpStatus.OK);
    }

    @ExceptionHandler(UserDefineException.class)
    public ResponseEntity<ErrorResponse> handlerUserDefineException(UserDefineException e){
        ResponseFormat responseFormat = ResponseFormat.fail(e.getMessage());
        return new ResponseEntity(responseFormat, HttpStatus.OK);
    }
}
