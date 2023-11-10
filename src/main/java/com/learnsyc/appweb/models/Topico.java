package com.learnsyc.appweb.models;

import java.time.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import com.learnsyc.appweb.models.Categoria;
@Data
@Table(name="topicos")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Topico {
    @Id //Identifica a la primary key
    @GeneratedValue(strategy= GenerationType.AUTO) //Hace un autoincrement
    @Column(name="id_topico") //Para que ubique a que columna agregar el valor
    Long idTopico;
    @Column(name="nombre")
    String nombre;
    @Column(name="descripcion")
    String descripcion;
    @Column(name="fecha_creacion")
    final LocalDate fechaCreacion = LocalDate.now();
    //Cambiar a guion bajo los name
    @JoinColumns({
        @JoinColumn(name="id_categorias", referencedColumnName="id_categorias")
    })
    @ManyToOne
    Categoria categoria;
}
