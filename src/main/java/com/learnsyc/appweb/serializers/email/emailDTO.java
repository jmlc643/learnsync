package com.learnsyc.appweb.serializers.email;

public record emailDTO(String[] toUser,
                    String subject,
                    String message){
}

