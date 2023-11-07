package com.learnsyc.appweb.services;

import java.util.List;

import com.learnsyc.appweb.models.Categoria;
import com.learnsyc.appweb.serializers.categoria.CategoriaSerializer;
import com.learnsyc.appweb.serializers.topico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnsyc.appweb.models.Topico;
import com.learnsyc.appweb.repositories.TopicoRepository;

@Service
public class TopicoService {
    
    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    CategoriaService categoriaService;

    public List<Topico> listarTopico(){
        return topicoRepository.findAll();
    }

    public Topico guardarTopico(SaveTopicoRequest request){
        Categoria categoria = categoriaService.encontrarCategoria(request.getNombreCategoria());
        Topico topico = new Topico(null, request.getNombre(), request.getDescripcion(), categoria);
        return topicoRepository.save(topico);
    }

    public Topico encontrarTopico(String nombre) {
        return topicoRepository.findByNombre(nombre);
    }

    public Topico guardarCambios(Topico topico){
        return topicoRepository.saveAndFlush(topico);
    }

    public Topico eliminarTopico(EliminarTopicoRequest request){
        Topico topico = encontrarTopico(request.getEliminarTopico());
        topicoRepository.deleteById(topico.getIdTopico());
        return topico;
    }

    public TopicoSerializer editarTopico(EditarTopicoRequest request){
        Topico topico = encontrarTopico(request.getCambiarTopico());
        topico.setNombre(request.getNombre());
        topico.setDescripcion(request.getDescripcion());
        guardarCambios(topico);
        return retornarTopico(topico);
    }

    public TopicoSerializer buscarTopico(BuscarTopicoRequest request){
        try{
            Topico topico = encontrarTopico(request.getNombre());
            return retornarTopico(topico);
        }catch (Exception e){
            System.out.println("No existe el topico "+request.getNombre());
            return new TopicoSerializer();
        }
    }

    public TopicoSerializer retornarTopico(Topico topico){
        return new TopicoSerializer(topico.getNombre(), topico.getDescripcion(),
                new CategoriaSerializer(topico.getCategoria().getNombre(), topico.getCategoria().getDescripcion()));
    }
}
