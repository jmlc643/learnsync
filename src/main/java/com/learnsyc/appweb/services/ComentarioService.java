package com.learnsyc.appweb.services;

import com.learnsyc.appweb.models.Comentario;
import com.learnsyc.appweb.models.Hilo;
import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.repositories.ComentarioRepository;
import com.learnsyc.appweb.serializers.comentario.ComentarioSerializer;
import com.learnsyc.appweb.serializers.comentario.DeleteComentarioRequest;
import com.learnsyc.appweb.serializers.comentario.EditComentarioRequest;
import com.learnsyc.appweb.serializers.comentario.SaveComentarioRequest;
import com.learnsyc.appweb.serializers.usuario.UserSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    ComentarioRepository comentarioRepository;
    @Autowired
    UserService userService;
    @Autowired
    HiloService hiloService;

    public List<Comentario> listarComentario(){return comentarioRepository.findAll();}

    public Comentario guardarComentario(SaveComentarioRequest request){
        Usuario usuario = userService.encontrarUsuario(request.getUsername());
        Hilo hilo = hiloService.encontrarHilo(request.getIdHilo());
        Comentario comentario = new Comentario(null, request.getMensaje(), hilo, usuario);
        return comentarioRepository.save(comentario);
    }

    public Comentario encontrarComentario(Long id){
        return comentarioRepository.findByIdComentario(id);
    }

    public Comentario guardarCambios(Comentario comentario){return comentarioRepository.saveAndFlush(comentario);}

    public Comentario eliminarComentario(DeleteComentarioRequest request){
        Comentario comentario = encontrarComentario(request.getId());
        comentarioRepository.deleteById(comentario.getIdComentario());
        return comentario;
    }

    public ComentarioSerializer editarComentario(EditComentarioRequest request){
        Comentario comentario = encontrarComentario(request.getId());
        comentario.setMensaje(request.getMensaje());
        comentario.setEsEditado(true);
        guardarCambios(comentario);
        return retornarComentario(comentario);
    }

    public ComentarioSerializer retornarComentario(Comentario comentario){
        return new ComentarioSerializer(comentario.getIdComentario(), comentario.getMensaje(), comentario.isEsEditado(), hiloService.retornarHilo(comentario.getHilo()),
                new UserSerializer(comentario.getUsuario().getUser(), comentario.getUsuario().getEmail()));
    }
}
