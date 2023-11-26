package com.learnsyc.appweb.excepciones;

public class ExpiredToken extends RuntimeException {
    public ExpiredToken(String message) {super(message);}
}
