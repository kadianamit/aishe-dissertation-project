package aishe.gov.in.exception;

import java.util.List;

public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366163L;

    private List<String> errors;

    public ValidationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ValidationException(final String message) {
        super(message);
    }

    /**
     * Call this constructor to send errors/exceptions as a list
     * @DateTime 22-01-2021
     * @Author Utkarsh
     */
    public ValidationException(List<String> errors){
        super();
        this.errors = errors;
    }

    public ValidationException(final Throwable cause) {
        super(cause);
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}
