package aishe.gov.in.exception;


import aishe.gov.in.utility.ReturnResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * GlobleExceptionHandler
 *
 * @author Dev Raj
 * @version 1.0
 */
@ControllerAdvice
public class GlobleExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobleExceptionHandler.class);

    /**
     * handleEntityException
     *
     * @param ex      EntityException
     * @param request WebRequest
     * @return ResponseEntity<ReturnResponse>
     */
    @ExceptionHandler(EntityException.class)
    public final ResponseEntity<ReturnResponse> handleEntityException(EntityException ex, WebRequest request) {
        LOGGER.error("Entity exception : " + ex);
        ReturnResponse exceptionResponse = new ReturnResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                ex.getMessage(), request.getDescription(false));
        LOGGER.error("Entity exception : " + exceptionResponse);
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
        LOGGER.error("UnAuthorization Exception: " + ex);
        ReturnResponse exceptionResponse = new ReturnResponse(HttpStatus.PAYMENT_REQUIRED.value(), ex.getMessage(), request.getDescription(false));
        LOGGER.error("UnAuthorization Exception : " + exceptionResponse);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.PAYMENT_REQUIRED);
    }

    @ExceptionHandler(InvalidInputException.class)
    public final ResponseEntity<ReturnResponse> handleInvalidInputException(InvalidInputException ex, WebRequest request) {
        LOGGER.error("UnAuthorization Exception: " + ex);
        ReturnResponse exceptionResponse = new ReturnResponse(HttpStatus.CONFLICT.value(), ex.getMessage(),
                request.getDescription(false));
        LOGGER.error("UnAuthorization Exception : " + exceptionResponse);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }
}
