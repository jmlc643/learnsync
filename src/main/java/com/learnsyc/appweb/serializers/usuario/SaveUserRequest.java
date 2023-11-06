package com.learnsyc.appweb.serializers.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class SaveUserRequest {
    @NotNull @NotEmpty String user;
    @NotNull @NotEmpty String password;
    @NotNull @Email @NotEmpty String email;
}