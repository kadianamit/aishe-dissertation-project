package aishe.gov.in.exception;

public class ExpressionNotVerifiedException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366163L;

    public ExpressionNotVerifiedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ExpressionNotVerifiedException(final String message) {
        super(message);
    }

    public ExpressionNotVerifiedException(final Throwable cause) {
        super(cause);
    }
}
