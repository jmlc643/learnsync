package com.learnsyc.appweb.serializers.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuscarTopicoRequest {
    @NotEmpty(message = "Dato vacio")
    @NotBlank(message = "No es valido un dato con solo espacio en blanco")
    String nombre;
}
