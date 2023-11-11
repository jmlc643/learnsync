package com.learnsyc.appweb.controllers;

import com.learnsyc.appweb.models.Archivo;
import com.learnsyc.appweb.models.Comentario;
import com.learnsyc.appweb.serializers.archivo.ArchivoSerializer;
import com.learnsyc.appweb.serializers.archivo.SaveArchivoRequest;
import com.learnsyc.appweb.services.ArchivoService;
import com.learnsyc.appweb.services.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("archivo")
@CrossOrigin(origins = "http://localhost:4200")
public class ArchivoController {
    @Autowired ArchivoService archivoService;
    @Autowired ComentarioService comentarioService;

    @GetMapping("/")
    public List<ArchivoSerializer> mostrarArchivo(){
        return archivoService.listarArchivo().stream().map((it) -> archivoService.retornarArchivo(it)).toList();
    }

    @PostMapping("/")
    public Archivo subirArchivo(@Valid @RequestBody SaveArchivoRequest request){
        Comentario comentario = comentarioService.encontrarComentario(request.getId());
        Archivo archivo = new Archivo(null, request.getNombre(), request.getTipo(), request.getLink(), comentario);
        return archivoService.guardarArchivo(archivo);
    }
}