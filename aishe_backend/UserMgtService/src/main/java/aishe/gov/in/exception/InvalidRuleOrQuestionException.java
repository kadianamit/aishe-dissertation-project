package aishe.gov.in.exception;

public class InvalidRuleOrQuestionException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366163L;

    public InvalidRuleOrQuestionException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidRuleOrQuestionException(final String message) {
        super(message);
    }

    public InvalidRuleOrQuestionException(final Throwable cause) {
        super(cause);
    }
}
