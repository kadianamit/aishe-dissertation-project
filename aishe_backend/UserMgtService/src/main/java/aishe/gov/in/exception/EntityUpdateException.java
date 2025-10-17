package aishe.gov.in.exception;

public class EntityUpdateException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366163L;

    public EntityUpdateException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EntityUpdateException(final String message) {
        super(message);
    }

    public EntityUpdateException(final Throwable cause) {
        super(cause);
    }
}
