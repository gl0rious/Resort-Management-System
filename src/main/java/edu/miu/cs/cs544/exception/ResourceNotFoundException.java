package edu.miu.cs.cs544.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(Class<?> clazz, int id) {
        super(String.format("%s with id: %d Not Found", clazz.getName() , id));
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ResourceNotFoundException(String message,
                                        Throwable cause,
                                        boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
