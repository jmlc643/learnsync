package com.learnsyc.appweb.services;

import com.learnsyc.appweb.excepciones.ResourceNotExistsException;
import com.learnsyc.appweb.models.Hilo;
import com.learnsyc.appweb.models.Topico;
import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.repositories.HiloRepository;
import com.learnsyc.appweb.serializers.hilos.DeleteHiloRequest;
import com.learnsyc.appweb.serializers.hilos.HiloSerializer;
import com.learnsyc.appweb.serializers.hilos.MoveHiloRequest;
import com.learnsyc.appweb.serializers.hilos.SaveHiloRequest;
import com.learnsyc.appweb.serializers.hilos.EditHiloRequest;
import com.learnsyc.appweb.serializers.usuario.UserSerializer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HiloService {

    @Autowired
    HiloRepository hiloRepository;
    @Autowired
    TopicoService topicoService;

    public List<Hilo> listarHilo() {
        return hiloRepository.findAll();
    }

    public Hilo guardarHilo(Hilo hilo) {
        return hiloRepository.save(hilo);
    }

    public Hilo encontrarHilo(Long id) {
        if (!hiloRepository.existsById(id)) {
            throw new ResourceNotExistsException("El hilo #" + id + " no existe");
        }
        return hiloRepository.findByIdHilo(id);
    }

    public Hilo eliminarHilo(DeleteHiloRequest request) {
        Hilo hilo = encontrarHilo(request.getId());
        hiloRepository.deleteById(request.getId());
        return hilo;
    }

    public Hilo guardarCambios(Hilo hilo) {
        return hiloRepository.saveAndFlush(hilo);
    }

    public HiloSerializer retornarHilo(Hilo hilo) {
        return new HiloSerializer(hilo.getIdHilo(), hilo.getTitulo(), hilo.getMensaje(), hilo.isCerrado(), hilo.getFechaCreacion(),
                topicoService.retornarTopico(hilo.getTopico()), new UserSerializer(hilo.getUsuario().getUser(), hilo.getUsuario().getEmail(), hilo.getUsuario().getNroPuntos()));
    }

    public HiloSerializer cerrarHilo(DeleteHiloRequest request) {
        Hilo hilo = encontrarHilo(request.getId());
        hilo.setCerrado(true);
        guardarCambios(hilo);
        return retornarHilo(hilo);
    }

    public HiloSerializer moverHilo(MoveHiloRequest request) {
        Hilo hilo = encontrarHilo(request.getId());
        Topico topico = topicoService.buscarTopico(request.getNombreTopico());
        hilo.setTopico(topico);
        guardarCambios(hilo);
        return retornarHilo(hilo);
    }

    public HiloSerializer editarHilo(EditHiloRequest request) {
        Hilo hilo = encontrarHilo(request.getId());
        hilo.setTitulo(request.getTitulo());
        hilo.setMensaje(request.getMensaje());
        guardarCambios(hilo);
        return retornarHilo(hilo);
    }
}