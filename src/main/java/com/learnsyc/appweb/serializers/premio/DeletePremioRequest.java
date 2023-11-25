package com.learnsyc.appweb.serializers.premio;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeletePremioRequest {
    @NotNull(message = "Dato vacio")
    Long id;
}