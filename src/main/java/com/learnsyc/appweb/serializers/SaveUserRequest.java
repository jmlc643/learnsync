package com.learnsyc.appweb.serializers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveUserRequest {
    String user;
    String password;
    String email;
}