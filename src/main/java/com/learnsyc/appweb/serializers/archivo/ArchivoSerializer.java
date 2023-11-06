package com.learnsyc.appweb.serializers.archivo;

import com.learnsyc.appweb.serializers.comentario.ComentarioSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArchivoSerializer {
    String nombre;
    String tipo;
    String link;
    ComentarioSerializer comentario;
}
