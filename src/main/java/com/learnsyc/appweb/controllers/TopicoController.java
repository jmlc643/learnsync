package com.learnsyc.appweb.controllers;

import java.util.List;

import com.learnsyc.appweb.serializers.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.learnsyc.appweb.models.Topico;
import com.learnsyc.appweb.models.Categoria;
import com.learnsyc.appweb.serializers.categoria.CategoriaSerializer;
import com.learnsyc.appweb.services.TopicoService;
import com.learnsyc.appweb.services.CategoriaService;

@RestController
@RequestMapping("topico")
//@CrossOrigin(origins = "http://localhost:4200")
public class TopicoController {
    @Autowired TopicoService topicoService; 
    @Autowired CategoriaService categoriaService;

    @GetMapping("/")
    public List<TopicoSerializer> listarTopico() {
        return topicoService.listarTopico().stream().map((it) -> new TopicoSerializer(it.getNombre(), it.getDescripcion(), new CategoriaSerializer(it.getCategoria().getNombre(), it.getCategoria().getDescripcion()))).toList();
    }

    @PostMapping("/")
    public Topico crearTopico(@Valid @RequestBody SaveTopicoRequest request) {
        Categoria categoria = categoriaService.encontrarCategoria(request.getNombreCategoria());
        Topico topico = new Topico(null, request.getNombre(), request.getDescripcion(), categoria);
        topicoService.guardarTopico(topico);
        return topico;
    }

    @PutMapping("/")
    public Topico editarTopico(@Valid @RequestBody EditarTopicoRequest request){
        Topico topico = topicoService.encontrarTopico(request.getCambiarTopico());
        topico.setNombre(request.getNombre());
        topico.setDescripcion(request.getDescripcion());
        topicoService.guardarCambios(topico);
        return topico;
    }

    @DeleteMapping("/")
    public Topico eliminarTopico(@Valid @RequestBody EliminarTopicoRequest request){
        Topico topico = topicoService.encontrarTopico(request.getEliminarTopico());
        topicoService.eliminarTopico(topico.getIdTopico());
        return topico;
    }

    @GetMapping("/buscar/")
    public TopicoSerializer buscarTopico(@Valid @RequestBody BuscarTopicoRequest request){
        //Buscar por hilos y comentarios (IDEA)
        try{
            Topico topico = topicoService.encontrarTopico(request.getNombre());
            return new TopicoSerializer(topico.getNombre(), topico.getDescripcion(),
                    new CategoriaSerializer(topico.getCategoria().getNombre(), topico.getCategoria().getDescripcion()));
        }catch (Exception e){
            System.out.println("No existe el topico "+request.getNombre());
            return new TopicoSerializer();
        }
    }
}
