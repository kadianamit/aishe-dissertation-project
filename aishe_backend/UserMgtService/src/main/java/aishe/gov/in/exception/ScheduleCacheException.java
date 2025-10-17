package aishe.gov.in.exception;

public class ScheduleCacheException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366163L;

    public ScheduleCacheException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ScheduleCacheException(final String message) {
        super(message);
    }

    public ScheduleCacheException(final Throwable cause) {
        super(cause);
    }
}
