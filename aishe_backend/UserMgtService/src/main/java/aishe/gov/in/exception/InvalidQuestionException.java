package aishe.gov.in.exception;

public class InvalidQuestionException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366163L;

    public InvalidQuestionException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidQuestionException(final String message) {
        super(message);
    }

    public InvalidQuestionException(final Throwable cause) {
        super(cause);
    }
}
