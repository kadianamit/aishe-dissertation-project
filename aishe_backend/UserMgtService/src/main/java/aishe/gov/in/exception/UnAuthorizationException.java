package aishe.gov.in.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * UnAuthorizationException
 *
 * @author Dev Raj
 * @version 1.0 05-06-2023
 */
@ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
public class UnAuthorizationException extends RuntimeException {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EntityException.class);
    /**
     * message
     */
    private String message;


        public UnAuthorizationException(String message) {
        super(message);
        LOGGER.error("exception : " + message);
    }

}
