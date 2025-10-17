package aishe.gov.in.exception;

public class AuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366163L;

    public AuthenticationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AuthenticationException(final String message) {
        super(message);
    }

    public AuthenticationException(final Throwable cause) {
        super(cause);
    }

}
