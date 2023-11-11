package com.learnsyc.appweb.serializers.topico;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.learnsyc.appweb.serializers.categoria.CategoriaSerializer;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicoSerializer {
    String nombre;
    String descripcion;
    CategoriaSerializer categoria;
}
