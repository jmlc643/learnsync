package com.learnsyc.appweb.serializers.hilos;

import com.learnsyc.appweb.serializers.topico.TopicoSerializer;
import com.learnsyc.appweb.serializers.usuario.UserSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HiloSerializer {
    String titulo;
    String mensaje;
    TopicoSerializer topico;
    UserSerializer usuario;
}
