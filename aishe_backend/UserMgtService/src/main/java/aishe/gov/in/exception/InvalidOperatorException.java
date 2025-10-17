package aishe.gov.in.exception;

public class InvalidOperatorException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366163L;

    public InvalidOperatorException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidOperatorException(final String message) {
        super(message);
    }

    public InvalidOperatorException(final Throwable cause) {
        super(cause);
    }
}
