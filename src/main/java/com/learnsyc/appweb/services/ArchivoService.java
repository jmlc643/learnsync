package com.learnsyc.appweb.services;

import com.learnsyc.appweb.models.Archivo;
import com.learnsyc.appweb.repositories.ArchivoRepository;
import com.learnsyc.appweb.serializers.archivo.ArchivoSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchivoService {
    @Autowired
    ArchivoRepository archivoRepository;

    @Autowired
    ComentarioService comentarioService;

    public List<Archivo> listarArchivo(){return archivoRepository.findAll();}

    public Archivo guardarArchivo(Archivo archivo){
        return archivoRepository.save(archivo);
    }

    public ArchivoSerializer retornarArchivo(Archivo archivo){return new ArchivoSerializer(archivo.getNombre(), archivo.getTipo(), archivo.getLink(),
            comentarioService.retornarComentario(archivo.getComentario()));
    }
}