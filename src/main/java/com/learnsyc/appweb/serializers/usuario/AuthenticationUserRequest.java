package com.learnsyc.appweb.serializers.usuario;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationUserRequest {
    @NotNull String user;
    @NotNull String password;
}
