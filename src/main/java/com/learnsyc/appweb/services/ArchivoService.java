package com.learnsyc.appweb.services;

import com.learnsyc.appweb.models.Archivo;
import com.learnsyc.appweb.models.Comentario;
import com.learnsyc.appweb.repositories.ArchivoRepository;
import com.learnsyc.appweb.serializers.archivo.ArchivoSerializer;
import com.learnsyc.appweb.serializers.archivo.SaveArchivoRequest;
import com.learnsyc.appweb.serializers.categoria.CategoriaSerializer;
import com.learnsyc.appweb.serializers.comentario.ComentarioSerializer;
import com.learnsyc.appweb.serializers.hilos.HiloSerializer;
import com.learnsyc.appweb.serializers.topico.TopicoSerializer;
import com.learnsyc.appweb.serializers.usuario.UserSerializer;
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

    public Archivo guardarArchivo(SaveArchivoRequest request){
        Comentario comentario = comentarioService.encontrarComentario(request.getId());
        Archivo archivo = new Archivo(null, request.getNombre(), request.getTipo(), request.getLink(), comentario);
        return archivoRepository.save(archivo);
    }

    public ArchivoSerializer retornarArchivo(Archivo archivo){return new ArchivoSerializer(archivo.getNombre(), archivo.getTipo(), archivo.getLink(),
            comentarioService.retornarComentario(archivo.getComentario()));
    }
}