package com.nic.aishe.master.handler;


import com.nic.aishe.master.dto.ReturnResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * GlobalExceptionHandler
 *
 * @author Dev Raj
 * @version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * LOGGER
     */
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final String UNAUTHORIZED_USER = "Unauthorised User(Token Expired)";
    /**
     * handleEntityException
     *
     * @param ex  InvalidInputException
     * @param request WebRequest
     * @return ResponseEntity<ReturnResponse>
     */
    @ExceptionHandler(InvalidInputException.class)
    public final ResponseEntity<ReturnResponse> handleEntityException(InvalidInputException ex, WebRequest request) {
        ReturnResponse exceptionResponse = new ReturnResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage(), null);
        logger.error("Entity exception : {}", exceptionResponse);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * this advice method for  handle UnAuthorizationException
     *
     * @param ex      UnAuthorizationException
     * @param request WebRequest
     * @return {@link ResponseEntity<ReturnResponse>}
     */
    @ExceptionHandler(UnAuthorizationException.class)
    public final ResponseEntity<ReturnResponse> handleUnAuthorizationException(UnAuthorizationException ex, WebRequest request) {
        logger.error("UnAuthorization Exception: " + ex);
        ReturnResponse exceptionResponse = new ReturnResponse(HttpStatus.PAYMENT_REQUIRED.value(), UNAUTHORIZED_USER, request.getDescription(false));
        logger.error("UnAuthorization Exception : " + exceptionResponse);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.PAYMENT_REQUIRED);
    }


}
