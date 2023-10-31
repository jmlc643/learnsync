package com.learnsyc.appweb.models;

import lombok.Data;
import java.time.*; //Para las fechas

@Data
public class Comentario {
    String mensaje;
    final LocalDateTime fechaCreacion = LocalDateTime.now();
}
