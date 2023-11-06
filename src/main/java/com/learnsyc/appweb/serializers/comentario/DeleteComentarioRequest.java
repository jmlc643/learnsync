package com.learnsyc.appweb.serializers.comentario;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteComentarioRequest {
    @NotNull(message = "Dato vacio")
    Long id;
}
