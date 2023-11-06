package com.learnsyc.appweb.controllers;

import com.learnsyc.appweb.models.Archivo;
import com.learnsyc.appweb.models.Comentario;
import com.learnsyc.appweb.serializers.archivo.ArchivoSerializer;
import com.learnsyc.appweb.serializers.archivo.SaveArchivoRequest;
import com.learnsyc.appweb.serializers.categoria.CategoriaSerializer;
import com.learnsyc.appweb.serializers.comentario.ComentarioSerializer;
import com.learnsyc.appweb.serializers.hilos.HiloSerializer;
import com.learnsyc.appweb.serializers.topico.TopicoSerializer;
import com.learnsyc.appweb.serializers.usuario.UserSerializer;
import com.learnsyc.appweb.services.ArchivoService;
import com.learnsyc.appweb.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("archivo")
//@CrossOrigin(origins = "http://localhost:4200")
public class ArchivoController {
    @Autowired ComentarioService comentarioService;
    @Autowired ArchivoService archivoService;

    @GetMapping("/")
    public List<ArchivoSerializer> mostrarArchivo(){
        return archivoService.listarArchivo().stream().map((it) -> new ArchivoSerializer(it.getNombre(), it.getTipo(), it.getLink(),
                new ComentarioSerializer(it.getComentario().getIdComentario(), it.getComentario().getMensaje(), it.getComentario().isEsEditado(),
                        new HiloSerializer(it.getComentario().getHilo().getIdHilo(), it.getComentario().getHilo().getTitulo(), it.getComentario().getHilo().getMensaje(),
                                new TopicoSerializer(it.getComentario().getHilo().getTopico().getNombre(), it.getComentario().getHilo().getTopico().getDescripcion(),
                                        new CategoriaSerializer(it.getComentario().getHilo().getTopico().getCategoria().getNombre(), it.getComentario().getHilo().getTopico().getCategoria().getDescripcion())),
                                new UserSerializer(it.getComentario().getHilo().getUsuario().getUser(), it.getComentario().getHilo().getUsuario().getEmail())),
                        new UserSerializer(it.getComentario().getUsuario().getUser(), it.getComentario().getUsuario().getEmail())))).toList();
    }

    @PostMapping("/")
    public Archivo subirArchivo(@RequestBody SaveArchivoRequest request){
        Comentario comentario = comentarioService.encontrarComentario(request.getId());
        Archivo archivo = new Archivo(null, request.getNombre(), request.getTipo(), request.getLink(), comentario);
        archivoService.guardarArchivo(archivo);
        return archivo;
    }
}
