package com.learnsyc.appweb.models;

import lombok.Data;
import java.time.*; //Para las fechas
@Data
public class Hilo {
    String titulo;
    String mensaje;
    boolean cerrado;
    final LocalDateTime fechaCreacion = LocalDateTime.now();

    public Hilo(String titulo, String mensaje){
        this.titulo = titulo;
        this.mensaje = mensaje;
        cerrado = false;
    }
}
