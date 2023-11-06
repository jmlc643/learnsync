package com.learnsyc.appweb.serializers.hilos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveHiloRequest {
    @NotNull String titulo;
    @NotNull String mensaje;
    @NotNull String username;
    @NotNull String topicname;

}
