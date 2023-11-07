package com.learnsyc.appweb.controllers;

import com.learnsyc.appweb.models.Archivo;
import com.learnsyc.appweb.serializers.archivo.ArchivoSerializer;
import com.learnsyc.appweb.serializers.archivo.SaveArchivoRequest;
import com.learnsyc.appweb.services.ArchivoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("archivo")
//@CrossOrigin(origins = "http://localhost:4200")
public class ArchivoController {
    @Autowired ArchivoService archivoService;

    //Pasar logica de los Controller con excepciones dentro de los Services

    @GetMapping("/")
    public List<ArchivoSerializer> mostrarArchivo(){
        return archivoService.listarArchivo().stream().map((it) -> archivoService.retornarArchivo(it)).toList();
    }

    @PostMapping("/")
    public Archivo subirArchivo(@Valid @RequestBody SaveArchivoRequest request){
        return archivoService.guardarArchivo(request);
    }
}