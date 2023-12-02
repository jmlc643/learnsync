package com.learnsyc.appweb.serializers.hilos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditHiloRequest {
    @NotEmpty(message = "Dato vacio")
    @NotBlank(message = "No es valido un dato con solo espacio en blanco")
    Long id;
    @NotEmpty(message = "Dato vacio")
    @NotBlank(message = "No es valido un dato con solo espacio en blanco")
    String titulo;
    @NotEmpty(message = "Dato vacio")
    @NotBlank(message = "No es valido un dato con solo espacio en blanco")
    String mensaje;
}
