package aishe.gov.in.exception;

public class InvalidAssessmentException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366163L;

    public InvalidAssessmentException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidAssessmentException(final String message) {
        super(message);
    }

    public InvalidAssessmentException(final Throwable cause) {
        super(cause);
    }
}
