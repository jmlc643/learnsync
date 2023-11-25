package com.learnsyc.appweb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "premios")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Premio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_premio")
    Long idPremio;

    @Column(name = "nombre", nullable = false)
    String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    String descripcion;

    @Column(name = "precio")
    int precio;

    @Lob
    @Column(name = "imagen")
    byte[] imagen;

    @JoinColumns({
            @JoinColumn(name="id_usuario", referencedColumnName="id_usuario")
    })
    @ManyToOne
    Usuario usuario;
}