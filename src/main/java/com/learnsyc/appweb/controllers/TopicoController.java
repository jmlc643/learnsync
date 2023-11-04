package com.learnsyc.appweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.learnsyc.appweb.models.Topico;
import com.learnsyc.appweb.models.Categoria;
import com.learnsyc.appweb.serializers.topico.TopicoSerializer;
import com.learnsyc.appweb.serializers.topico.SaveTopicoRequest;
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
        return topicoService.listarTopico().stream().map((it) -> new TopicoSerializer(it.getNombre(), it.getDescripcion(), it.getCategoria())).toList();
    }

    @PostMapping("/")
    public Topico crearTopico(@RequestBody SaveTopicoRequest request) {
        Categoria categoria = categoriaService.asignarCategoria(request.getNombreCategoria());
        Topico topico = new Topico(null, request.getNombre(), request.getDescripcion(), categoria.getIdCategorias(), categoria);
        topicoService.guardarTopico(topico);
        return topico;
    }
}
