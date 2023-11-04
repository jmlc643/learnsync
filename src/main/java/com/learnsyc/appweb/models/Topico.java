package com.learnsyc.appweb.models;

import java.time.*;
import jakarta.persistence.*;
import lombok.NoArgsConstructor; 
import lombok.Data;
import lombok.AllArgsConstructor;
import com.learnsyc.appweb.models.Categoria;
@Data
@Table(name="topicos") //Da a entender que sera un modelo de base de datos
@Entity //Da a entender que sera una entidad de base de datos
@NoArgsConstructor //Genera constructor vacio
@AllArgsConstructor
public class Topico {
    @Id //Identifica a la primary key
    @GeneratedValue(strategy= GenerationType.AUTO) //Hace un autoincrement
    @Column(name="idTopico") //Para que ubique a que columna agregar el valor
    Long idTopico;
    @Column(name="nombre")
    String nombre;
    @Column(name="descripcion")
    String descripcion;
    @Column(name="fechaCreacion")
    final LocalDateTime fechaCreacion = LocalDateTime.now();
    @Id
    @JoinColumns({
        @JoinColumn(name="id_categorias", referencedColumnName="id_categorias")
    })
    @ManyToOne
    Categoria categoria;
}
