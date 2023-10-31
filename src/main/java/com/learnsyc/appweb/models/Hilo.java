package com.learnsyc.appweb.models;

import lombok.Data;
import java.time.*; //Para las fechas
@Data
public class Hilo {
    String titulo;
    String mensaje;
    final LocalDateTime fechaCreacion = LocalDateTime.now();
}
