package com.parcel.delivery.cotroller;

import com.parcel.delivery.domain.response.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> handleException(final Exception ex){
        logger.error(ex.getLocalizedMessage());
        return new ResponseEntity<>(new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() + ":" + ex.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        return new ResponseEntity<>(new MessageResponse(details.toString()), HttpStatus.BAD_REQUEST);
    }
}
