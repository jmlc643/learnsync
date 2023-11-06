package com.learnsyc.appweb.serializers.archivo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveArchivoRequest {
    @NotNull String nombre;
    @NotNull String tipo;
    @NotNull String link;
    @NotNull Long id;
}
