package com.learnsyc.appweb.serializers.premio;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PremioSerializer {
    String nombre;
    String descripcion;
    int precio;
    byte[] imagen;
}