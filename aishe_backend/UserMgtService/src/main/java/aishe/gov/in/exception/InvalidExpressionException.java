package aishe.gov.in.exception;

public class InvalidExpressionException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366163L;

    public InvalidExpressionException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidExpressionException(final String message) {
        super(message);
    }

    public InvalidExpressionException(final Throwable cause) {
        super(cause);
    }
}
