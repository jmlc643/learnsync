package com.learnsyc.appweb.serializers.comentario;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveComentarioRequest {
    @NotEmpty(message = "Dato vacio")
    String mensaje;
    @NotEmpty(message = "Dato vacio")
    String username;
    @NotNull(message = "Dato vacio")
    Long idHilo;
}
