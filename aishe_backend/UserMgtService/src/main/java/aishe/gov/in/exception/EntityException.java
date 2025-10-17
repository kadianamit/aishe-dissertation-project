package aishe.gov.in.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * CellException
 *
 * @author Dev Raj
 * @version 1.0 2-18-2020
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class EntityException extends RuntimeException {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EntityException.class);
    /**
     * message
     */
    private String message;


        public EntityException(String message) {
        super(message);
        LOGGER.error("exception : " + message);
    }

}
