package com.learnsyc.appweb.serializers.topico;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditarTopicoRequest {
    @NotNull String cambiarTopico;
    @NotNull String nombre;
    @NotNull String descripcion;
}
