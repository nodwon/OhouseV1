package com.portfolio.ohousev1.repository.exception;

public class NotFoundException extends RuntimeException {
    public <T> NotFoundException(Class<T> type, String message)  {
        super(type.getName() + message);
    }
    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }
    public NotFoundException(String entityName, String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    protected NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }




}
