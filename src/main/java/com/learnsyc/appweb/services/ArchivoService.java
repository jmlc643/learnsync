package com.learnsyc.appweb.services;

import com.learnsyc.appweb.models.Archivo;
import com.learnsyc.appweb.repositories.ArchivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchivoService {
    @Autowired
    ArchivoRepository archivoRepository;

    public List<Archivo> listarArchivo(){return archivoRepository.findAll();}

    public Archivo guardarArchivo(Archivo archivo){return archivoRepository.save(archivo);}
}
