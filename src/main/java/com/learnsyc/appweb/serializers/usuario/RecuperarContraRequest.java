package com.learnsyc.appweb.serializers.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecuperarContraRequest {
    @Email(message = "Formato incorrecto")
    @NotEmpty(message = "Dato vacio")
    String email;
}
