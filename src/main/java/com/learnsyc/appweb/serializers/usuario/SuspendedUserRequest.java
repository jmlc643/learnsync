package com.learnsyc.appweb.serializers.usuario;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SuspendedUserRequest {
    @NotNull String user;
    @NotNull LocalDateTime finSuspension;
}
