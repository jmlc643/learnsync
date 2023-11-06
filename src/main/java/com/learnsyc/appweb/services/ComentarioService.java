package com.learnsyc.appweb.services;

import com.learnsyc.appweb.models.Comentario;
import com.learnsyc.appweb.repositories.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    ComentarioRepository comentarioRepository;

    public List<Comentario> listarComentario(){return comentarioRepository.findAll();}

    public Comentario guardarComentario(Comentario comentario){return comentarioRepository.save(comentario);}

    public Comentario encontrarComentario(Long id){
        return comentarioRepository.findByIdComentario(id);
    }

    public Comentario guardarCambios(Comentario comentario){return comentarioRepository.saveAndFlush(comentario);}

    public void eliminarComentario(Long id){comentarioRepository.deleteById(id);}
}
