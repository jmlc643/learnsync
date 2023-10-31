package com.learnsyc.appweb.models;

import java.time.*;
import lombok.Data;

@Data
public class Topico {
    String nombre;
    String descripcion;
    final LocalDateTime fechaCreacion = LocalDateTime.now();
}
