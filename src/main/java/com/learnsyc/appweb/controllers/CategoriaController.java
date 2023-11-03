package com.learnsyc.appweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.learnsyc.appweb.models.Categoria;
import com.learnsyc.appweb.serializers.categoria.CategoriaSerializer;
import com.learnsyc.appweb.services.CategoriaService;

@RestController
@RequestMapping("categoria")
//@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {
    
    @Autowired CategoriaService categoriaService; 

    @GetMapping("/")
    public List<CategoriaSerializer> listarCategoria() {
        return categoriaService.listarCategorias().stream().map((it) -> new CategoriaSerializer(it.getNombre(), it.getDescripcion())).toList();
    }

    @PostMapping("/")
    public Categoria crearCategoria(@RequestBody CategoriaSerializer request) {
        Categoria categoria = new Categoria(null, request.getNombre(), request.getDescripcion());
        categoriaService.guardarCategoria(categoria);
        return categoria;
    }
}
