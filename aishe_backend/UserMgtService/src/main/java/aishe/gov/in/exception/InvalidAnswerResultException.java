package aishe.gov.in.exception;

public class InvalidAnswerResultException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366163L;

    public InvalidAnswerResultException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidAnswerResultException(final String message) {
        super(message);
    }

    public InvalidAnswerResultException(final Throwable cause) {
        super(cause);
    }
}
