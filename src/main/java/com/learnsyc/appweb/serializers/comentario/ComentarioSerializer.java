package com.learnsyc.appweb.serializers.comentario;

import com.learnsyc.appweb.serializers.hilos.HiloSerializer;
import com.learnsyc.appweb.serializers.usuario.UserSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComentarioSerializer {
    String mensaje;
    HiloSerializer hiloSerializer;
    UserSerializer userSerializer;
}
