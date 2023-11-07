package com.learnsyc.appweb.controllers;

import java.util.List;

import com.learnsyc.appweb.serializers.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.learnsyc.appweb.models.Topico;
import com.learnsyc.appweb.serializers.categoria.CategoriaSerializer;
import com.learnsyc.appweb.services.TopicoService;

@RestController
@RequestMapping("topico")
//@CrossOrigin(origins = "http://localhost:4200")
public class TopicoController {
    @Autowired TopicoService topicoService;

    @GetMapping("/")
    public List<TopicoSerializer> listarTopico() {
        return topicoService.listarTopico().stream().map((it) -> topicoService.retornarTopico(it)).toList();
    }

    @PostMapping("/")
    public Topico crearTopico(@Valid @RequestBody SaveTopicoRequest request) {
        return topicoService.guardarTopico(request);
    }

    @PutMapping("/")
    public TopicoSerializer editarTopico(@Valid @RequestBody EditarTopicoRequest request){
        return topicoService.editarTopico(request);
    }

    @DeleteMapping("/")
    public Topico eliminarTopico(@Valid @RequestBody EliminarTopicoRequest request){
        return topicoService.eliminarTopico(request);
    }

    @GetMapping("/buscar/")
    public TopicoSerializer buscarTopico(@Valid @RequestBody BuscarTopicoRequest request){
        //Buscar por hilos y comentarios (IDEA)
        return topicoService.buscarTopico(request);
    }
}