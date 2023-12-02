package com.learnsyc.appweb.controllers;

import com.learnsyc.appweb.models.Reporte;
import com.learnsyc.appweb.serializers.reporte.DeleteReporteRequest;
import com.learnsyc.appweb.serializers.reporte.ReporteComentarioSerializer;
import com.learnsyc.appweb.serializers.reporte.ReporteHiloSerializer;
import com.learnsyc.appweb.serializers.reporte.SaveReporteRequest;
import com.learnsyc.appweb.services.ReporteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reporte")
@CrossOrigin(origins = {"https://boisterous-sopapillas-1c3767.netlify.app"})
public class ReporteController {

    @Autowired ReporteService reporteService;

    @GetMapping("/comentario/")
    public List<ReporteComentarioSerializer> listarReporteComentario(){
        return reporteService.listarReportes().stream().map((it) -> reporteService.retornarComentariosReportados(it)).toList();
    }

    @GetMapping("/hilo/")
    public List<ReporteHiloSerializer> listarReporteHilo(){
        return reporteService.listarReportes().stream().map((it) -> reporteService.retornarHilosReportados(it)).toList();
    }

    @PostMapping("/")
    public Reporte generarReporte(@Valid @RequestBody SaveReporteRequest request){
        return reporteService.generarReporte(request);
    }

    @DeleteMapping("/")
    public Reporte eliminarReporte(@Valid @RequestBody DeleteReporteRequest request){
        return reporteService.eliminarReporte(request);
    }
}
