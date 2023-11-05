package com.learnsyc.appweb.services;

import java.util.List;

import com.learnsyc.appweb.models.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnsyc.appweb.models.Topico;
import com.learnsyc.appweb.repositories.TopicoRepository;

@Service
public class TopicoService {
    
    @Autowired
    TopicoRepository topicoRepository;

    public List<Topico> listarTopico(){
        return topicoRepository.findAll();
    }

    public Topico guardarTopico(Topico topico){
        return topicoRepository.save(topico);
    }

    public Topico encontrarTopico(String nombre) {
        return topicoRepository.findByNombre(nombre);
    }
}
