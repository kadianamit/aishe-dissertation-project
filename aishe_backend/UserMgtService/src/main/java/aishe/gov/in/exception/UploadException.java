package aishe.gov.in.exception;

public class UploadException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366163L;

    public UploadException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UploadException(final String message) {
        super(message);
    }

    public UploadException(final Throwable cause) {
        super(cause);
    }
}
