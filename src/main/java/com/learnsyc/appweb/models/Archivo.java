package com.learnsyc.appweb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="archivos")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Archivo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id_archivo")
    Long idArchivo;
    @Column(name="nombre")
    String nombre;
    @Column(name="tipo")
    String tipo;
    @Column(name="link")
    String link;
    @JoinColumns({
            @JoinColumn(name="id_comentario", referencedColumnName="id_comentario")
    })
    @ManyToOne
    Comentario comentario;
}