package com.nic.aishe.master.handler;

import java.util.List;

public class InvalidInputException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366163L;

    private List<String> errors;

    public InvalidInputException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidInputException(final String message) {
        super(message);
    }
    
    public InvalidInputException(List<String> errors) {
        super();
        this.errors = errors;
    }

    public InvalidInputException(final Throwable cause) {
        super(cause);
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
