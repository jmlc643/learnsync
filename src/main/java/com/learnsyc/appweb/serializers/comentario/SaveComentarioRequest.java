package com.learnsyc.appweb.serializers.comentario;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveComentarioRequest {
    @NotNull String mensaje;
    @NotNull String username;
    @NotNull String nombreHilo;
}
