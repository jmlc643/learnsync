package com.learnsyc.appweb.services;

import com.learnsyc.appweb.models.Comentario;
import com.learnsyc.appweb.models.Hilo;
import com.learnsyc.appweb.models.Reporte;
import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.repositories.ReporteRepository;
import com.learnsyc.appweb.serializers.reporte.DeleteReporteRequest;
import com.learnsyc.appweb.serializers.reporte.ReporteComentarioSerializer;
import com.learnsyc.appweb.serializers.reporte.ReporteHiloSerializer;
import com.learnsyc.appweb.serializers.reporte.SaveReporteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ReporteService {

    @Autowired
    ReporteRepository reporteRepository;
    @Autowired UserService userService;
    @Autowired ComentarioService comentarioService;
    @Autowired HiloService hiloService;

    public Reporte generarReporte(SaveReporteRequest request) {
        Usuario usuarioReportado = userService.encontrarUsuarioPorUser(request.getUsernameReportado());
        Usuario usuarioReportador = userService.encontrarUsuarioPorUser(request.getUsernameReportador());
        Reporte reporte = new Reporte(null, request.getRazon(), usuarioReportado, usuarioReportador);
        if(Objects.equals(request.getEvidencia(), "Comentario")){
            Comentario comentario = comentarioService.encontrarComentario(request.getId());
            reporte.setComentario(comentario);
        }else{
            Hilo hilo = hiloService.encontrarHilo(request.getId());
            reporte.setHilo(hilo);
        }
        return reporteRepository.save(reporte);
    }

    public List<Reporte> listarReportes() {
        return reporteRepository.findAll();
    }

    public Reporte eliminarReporte(DeleteReporteRequest request) {
        Reporte reporte = encontrarReporte(request.getIdReporte());
        reporteRepository.deleteById(request.getIdReporte());
        return reporte;
    }

    private Reporte encontrarReporte(Long idReporte) {
        return reporteRepository.findByIdReporte(idReporte);
    }

    public ReporteComentarioSerializer retornarComentariosReportados(Reporte reporte){
        return new ReporteComentarioSerializer(reporte.getIdReporte(), reporte.getRazon(),
                userService.retornarUsuario(reporte.getUsuarioReportado()),
                userService.retornarUsuario(reporte.getUsuarioReportador()),
                comentarioService.retornarComentario(reporte.getComentario()));
    }

    public ReporteHiloSerializer retornarHilosReportados(Reporte reporte) {
        return new ReporteHiloSerializer(reporte.getIdReporte(), reporte.getRazon(),
                userService.retornarUsuario(reporte.getUsuarioReportado()),
                userService.retornarUsuario(reporte.getUsuarioReportador()),
                hiloService.retornarHilo(reporte.getHilo()));
    }
}
