package com.learnsyc.appweb.services;

import com.learnsyc.appweb.models.Hilo;
import com.learnsyc.appweb.repositories.HiloRepository;
import com.learnsyc.appweb.serializers.categoria.CategoriaSerializer;
import com.learnsyc.appweb.serializers.hilos.HiloSerializer;
import com.learnsyc.appweb.serializers.topico.TopicoSerializer;
import com.learnsyc.appweb.serializers.usuario.UserSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HiloService {
    
    @Autowired
    HiloRepository hiloRepository;

    public List<Hilo> listarHilo(){
        return hiloRepository.findAll();
    }

    public Hilo guardarHilo(Hilo hilo){
        return hiloRepository.save(hilo);
    }

    public Hilo encontrarHilo(Long id){
        return hiloRepository.findByIdHilo(id);
    }

    public void eliminarHilo(Long id){hiloRepository.deleteById(id);}

    public Hilo guardarCambios(Hilo hilo){return hiloRepository.saveAndFlush(hilo);}

    public HiloSerializer retornarHilo(Hilo hilo){
        return new HiloSerializer(hilo.getIdHilo(), hilo.getTitulo(), hilo.getMensaje(), hilo.isCerrado(), hilo.getFechaCreacion(),
                new TopicoSerializer(hilo.getTopico().getNombre(), hilo.getTopico().getDescripcion(),
                        new CategoriaSerializer(hilo.getTopico().getCategoria().getNombre(), hilo.getTopico().getCategoria().getDescripcion())),
                new UserSerializer(hilo.getUsuario().getUser(), hilo.getUsuario().getEmail()));
    }
}
