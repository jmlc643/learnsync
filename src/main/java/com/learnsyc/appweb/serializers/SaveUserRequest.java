package com.learnsyc.appweb.serializers;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class SaveUserRequest {
    @NotNull String user;
    @NotNull String password;
    @NotNull String email;
}