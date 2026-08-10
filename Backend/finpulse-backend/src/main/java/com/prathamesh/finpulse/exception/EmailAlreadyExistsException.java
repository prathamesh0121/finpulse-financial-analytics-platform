package com.prathamesh.finpulse.exception;

public class EmailAlreadyExistsException extends  RuntimeException{

    public EmailAlreadyExistsException(String message)
    {
        super(message);
    }
}
