package com.learnsyc.appweb.serializers.topico;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EliminarTopicoRequest {
    @NotNull String eliminarTopico;
    //@NotNull Long id;
}