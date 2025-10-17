package aishe.gov.in.exception;

public class InvalidAnswerException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366163L;

    public InvalidAnswerException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidAnswerException(final String message) {
        super(message);
    }

    public InvalidAnswerException(final Throwable cause) {
        super(cause);
    }
}
