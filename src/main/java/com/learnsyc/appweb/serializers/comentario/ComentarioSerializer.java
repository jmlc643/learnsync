package com.learnsyc.appweb.serializers.comentario;

import com.learnsyc.appweb.serializers.hilos.HiloSerializer;
import com.learnsyc.appweb.serializers.usuario.UserSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComentarioSerializer {
    Long id;
    String mensaje;
    boolean esEditado;
    HiloSerializer hiloSerializer;
    UserSerializer userSerializer;
}
