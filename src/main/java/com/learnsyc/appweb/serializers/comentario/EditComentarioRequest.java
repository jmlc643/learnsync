package com.learnsyc.appweb.serializers.comentario;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditComentarioRequest {
    @NotNull Long id;
    @NotNull String mensaje;
}
