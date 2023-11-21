package com.learnsyc.appweb.models;

public record Email(String[] toUser,
                    String subject,
                    String message){
}
