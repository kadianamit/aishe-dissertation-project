package aishe.gov.in.exception;

public class InvalidRuleException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366163L;

    public InvalidRuleException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidRuleException(final String message) {
        super(message);
    }

    public InvalidRuleException(final Throwable cause) {
        super(cause);
    }
}
