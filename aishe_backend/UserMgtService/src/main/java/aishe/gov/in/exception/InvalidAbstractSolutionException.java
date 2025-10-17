package aishe.gov.in.exception;

public class InvalidAbstractSolutionException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366163L;

    public InvalidAbstractSolutionException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidAbstractSolutionException(final String message) {
        super(message);
    }

    public InvalidAbstractSolutionException(final Throwable cause) {
        super(cause);
    }
}
