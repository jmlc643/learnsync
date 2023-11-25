package com.learnsyc.appweb.services;

import com.learnsyc.appweb.excepciones.ResourceNotExistsException;
import com.learnsyc.appweb.models.Premio;
import com.learnsyc.appweb.repositories.PremioRepository;
import com.learnsyc.appweb.serializers.premio.DeletePremioRequest;
import com.learnsyc.appweb.serializers.premio.EditPremioRequest;
import com.learnsyc.appweb.serializers.premio.PremioSerializer;
import com.learnsyc.appweb.serializers.premio.SavePremioRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PremioService {

    @Autowired
    private PremioRepository premioRepository;

    public List<Premio> listarPremios() {
        return premioRepository.findAll();
    }

    public Premio guardarPremio(SavePremioRequest request) {
        Premio premio = new Premio();
        BeanUtils.copyProperties(request, premio);
        return premioRepository.save(premio);
    }

    public Premio editarPremio(EditPremioRequest request) {
        Premio premio = encontrarPremio(request.getId());
        BeanUtils.copyProperties(request, premio);
        return premioRepository.save(premio);
    }

    public Premio crearPremio(SavePremioRequest request) {
        Premio premio = new Premio();
        BeanUtils.copyProperties(request, premio);
        return premioRepository.save(premio);
    }

    public Premio eliminarPremio(DeletePremioRequest request) {
        Premio premio = encontrarPremio(request.getId());
        premioRepository.deleteById(request.getId());
        return premio;
    }

    public PremioSerializer retornarPremio(Long idPremio) {
        Premio premio = encontrarPremio(idPremio);
        return new PremioSerializer(premio.getNombre(), premio.getDescripcion(), premio.getPrecio(), premio.getImagen());
    }

    private Premio encontrarPremio(Long idPremio) {
        return premioRepository.findById(idPremio).orElseThrow(() ->
                new ResourceNotExistsException("El premio con ID " + idPremio + " no existe."));
    }
}