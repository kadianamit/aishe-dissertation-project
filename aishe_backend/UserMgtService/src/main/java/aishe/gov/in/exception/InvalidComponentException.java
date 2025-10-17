package aishe.gov.in.exception;

public class InvalidComponentException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366163L;

    public InvalidComponentException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidComponentException(final String message) {
        super(message);
    }

    public InvalidComponentException(final Throwable cause) {
        super(cause);
    }
}
