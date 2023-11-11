package com.learnsyc.appweb.excepciones;

public class BannedUserException extends RuntimeException{
    public BannedUserException(String message){super(message);}
}
