package com.learnsyc.appweb.services;

import com.learnsyc.appweb.models.Hilo;
import com.learnsyc.appweb.repositories.HiloRepository;
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

    public Hilo encontrarHIlo(String titulo){
        return hiloRepository.findByTitulo(titulo);
    }
}
