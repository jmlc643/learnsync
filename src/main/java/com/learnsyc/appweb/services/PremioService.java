package com.learnsyc.appweb.services;

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

@Service
public class PremioService {
    @Autowired
    private PremioRepository premioRepository;
    @Autowired
    private UserService userService;

    public List<Premio> listarPremios() {
        return premioRepository.findAll();
    }

    private Premio guardarCambios(Premio premio) {
        return premioRepository.saveAndFlush(premio);
    }

    public PremioSerializer editarPremio(EditPremioRequest request) {
        Premio premio = encontrarPremio(request.getId());
        premio.setNombre(request.getNombre());
        premio.setDescripcion(request.getDescripcion());
        premio.setPrecio(request.getPrecio());
        premio.setImagen(request.getImage());
        guardarCambios(premio);
        return retornarPremio(premio);
    }

    public Premio crearPremio(Premio premio) {
        return premioRepository.save(premio);
    }

    public Premio eliminarPremio(DeletePremioRequest request) {
        Premio premio = encontrarPremio(request.getId());
        premioRepository.deleteById(request.getId());
        return premio;
    }

    public PremioSerializer retornarPremio(Premio premio) {
        return new PremioSerializer(premio.getNombre(), premio.getDescripcion(), premio.getPrecio(), premio.getImagen());
    }

    private Premio encontrarPremio(Long id) {
        return premioRepository.findByIdPremio(id);
    }
}