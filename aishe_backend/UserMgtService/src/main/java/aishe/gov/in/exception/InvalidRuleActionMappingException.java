package aishe.gov.in.exception;

public class InvalidRuleActionMappingException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366163L;

    public InvalidRuleActionMappingException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidRuleActionMappingException(final String message) {
        super(message);
    }

    public InvalidRuleActionMappingException(final Throwable cause) {
        super(cause);
    }
}
