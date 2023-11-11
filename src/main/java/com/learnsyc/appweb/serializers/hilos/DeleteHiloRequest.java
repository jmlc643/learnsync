package com.learnsyc.appweb.serializers.hilos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteHiloRequest {
    @NotNull(message = "Dato vacio")
    Long id;
}
