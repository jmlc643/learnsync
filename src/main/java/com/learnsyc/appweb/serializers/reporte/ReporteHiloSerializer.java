package com.learnsyc.appweb.serializers.reporte;

import com.learnsyc.appweb.serializers.hilos.HiloSerializer;
import com.learnsyc.appweb.serializers.usuario.UserSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReporteHiloSerializer {
    Long idReporte;
    String razon;
    UserSerializer usuarioReportado;
    UserSerializer usuarioReportador;
    HiloSerializer hilo;
}
