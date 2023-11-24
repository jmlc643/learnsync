package com.learnsyc.appweb.serializers.reporte;

import com.learnsyc.appweb.serializers.comentario.ComentarioSerializer;
import com.learnsyc.appweb.serializers.usuario.UserSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReporteComentarioSerializer {
    Long idReporte;
    String razon;
    UserSerializer usuarioReportado;
    UserSerializer usuarioReportador;
    ComentarioSerializer comentario;
}
