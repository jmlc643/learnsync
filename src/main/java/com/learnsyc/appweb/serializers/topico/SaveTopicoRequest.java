package com.learnsyc.appweb.serializers.topico;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class SaveTopicoRequest {    
    @NotNull String nombre;
    @NotNull String descripcion;
    @NotNull String nombreCategoria;
}
