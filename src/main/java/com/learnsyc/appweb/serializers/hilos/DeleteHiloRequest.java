package com.learnsyc.appweb.serializers.hilos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteHiloRequest {
    @NotEmpty Long id;
}
