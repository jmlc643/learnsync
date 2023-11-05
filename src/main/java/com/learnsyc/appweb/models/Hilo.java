package com.learnsyc.appweb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.*; //Para las fechas
@Data
@Table(name="hilos")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Hilo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id_hilo")
    Long idHilo;
    @Column(name="titulo")
    String titulo;
    @Column(name="mensaje")
    String mensaje;
    @Column(name="cerrado")
    boolean cerrado;
    @Column(name="fecha_creacion")
    final LocalDateTime fechaCreacion = LocalDateTime.now();
    @JoinColumns({
            @JoinColumn(name="id_topico", referencedColumnName="id_topico")
    })
    @ManyToOne
    Topico topico;

    @JoinColumns({
            @JoinColumn(name="id_usuario", referencedColumnName="id_usuario")
    })
    @ManyToOne
    Usuario usuario;
    public Hilo(Long idHilo, String titulo, String mensaje, Topico topico, Usuario usuario){
        this.idHilo = idHilo;
        this.titulo = titulo;
        this.mensaje = mensaje;
        cerrado = false;
        this.topico = topico;
        this.usuario = usuario;
    }
}
