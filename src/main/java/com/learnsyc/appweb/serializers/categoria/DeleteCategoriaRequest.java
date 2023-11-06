package com.learnsyc.appweb.serializers.categoria;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteCategoriaRequest {
    @NotNull String nombre;
}
