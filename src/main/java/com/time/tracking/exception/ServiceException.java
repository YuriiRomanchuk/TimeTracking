package com.reservation.exception;

public class ServiceException extends Exception{

    public ServiceException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ServiceException(String s) {
        super(s);
    }
}
