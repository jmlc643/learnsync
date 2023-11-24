package com.learnsyc.appweb.services;

import com.learnsyc.appweb.models.Reporte;
import com.learnsyc.appweb.repositories.ReporteRepository;
import com.learnsyc.appweb.excepciones.ResourceNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteService {

    @Autowired
    ReporteRepository reporteRepository;

    public Reporte generarReporte(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public List<Reporte> listarReportes() {
        return reporteRepository.findAll();
    }

    public Reporte eliminarReporte(Long idReporte) {
        Reporte reporte = encontrarReporte(idReporte);
        reporteRepository.deleteById(idReporte);
        return reporte;
    }

    private Reporte encontrarReporte(Long idReporte) {
        return reporteRepository.findById(idReporte)
                .orElseThrow(() -> new ResourceNotExistsException("El reporte con ID " + idReporte + " no existe"));
    }
}
