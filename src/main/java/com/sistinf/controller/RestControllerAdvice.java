package com.sistinf.controller;

import com.sistinf.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    /*

Inserendo l'annotation @ExceptionHandler(MethodArgumentNotValidException.class)
sopra il metodo che abbiamo chiamato notValidExceptionHandler ogi volta che si
verificherà la violazione di un vincolo di validazione sulla chiamata da parte
di un Web Service Consumer la classe ExceptionAdvice (listener sui Controller
dell'applicazione) rileverà l'eccezione "catchandola", interpretrà l'oggetto
di risposta come un oggetto di eccezione grazie all'annotation @ExceptionHandler
e restituirà nel JSON di risposta le proprietà dell'Oggetto ErrorMessage
**/
    @ExceptionHandler(MethodArgumentNotValidException.class)@ResponseStatus(value= HttpStatus.BAD_REQUEST)
    public ErrorMessage notValidExceptionHandler(Exception ex, WebRequest request) {

        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(),
                "errore", request.getDescription(false));

        return message;

    }

}
