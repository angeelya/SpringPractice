package angeelya.spring.exceptionHandler.exception;

public class ValidationErrorsException extends Exception {
    public ValidationErrorsException(String message) {
        super(message);
    }
}
