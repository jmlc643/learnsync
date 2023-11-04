package com.learnsyc.appweb.models;

import java.time.*;
import lombok.Data;

@Data
public class Topico {
<<<<<<< Updated upstream
=======
    @Id //Identifica a la primary key
    @GeneratedValue(strategy= GenerationType.AUTO) //Hace un autoincrement
    @Column(name="id_topico") //Para que ubique a que columna agregar el valor
    Long idTopico;
    @Column(name="nombre")
>>>>>>> Stashed changes
    String nombre;
    String descripcion;
<<<<<<< Updated upstream
    final LocalDateTime fechaCreacion = LocalDateTime.now();
=======
    @Column(name="fecha_creacion")
    final LocalDateTime fechaCreacion = LocalDateTime.now();
    //Cambiar a guion bajo los name
    @JoinColumns({
        @JoinColumn(name="id_categorias", referencedColumnName="id_categorias")
    })
    @ManyToOne
    Categoria categoria;
>>>>>>> Stashed changes
}
