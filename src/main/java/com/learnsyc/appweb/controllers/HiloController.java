package com.learnsyc.appweb.controllers;

import com.learnsyc.appweb.models.Hilo;
import com.learnsyc.appweb.serializers.hilos.DeleteHiloRequest;
import com.learnsyc.appweb.serializers.hilos.HiloSerializer;
import com.learnsyc.appweb.serializers.hilos.MoveHiloRequest;
import com.learnsyc.appweb.serializers.hilos.SaveHiloRequest;
import com.learnsyc.appweb.services.HiloService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hilo")
//@CrossOrigin(origins = "http://localhost:4200")
public class HiloController {
    @Autowired HiloService hiloService;

    @GetMapping("/")
    public List<HiloSerializer> listarHilo() {
        return hiloService.listarHilo().stream().map((it) -> hiloService.retornarHilo(it)).toList();
    }

    @PostMapping("/")
    public Hilo crearHilo(@Valid @RequestBody SaveHiloRequest request) {
        return hiloService.guardarHilo(request);
    }

    @DeleteMapping("/")
    public Hilo eliminarHilo(@Valid @RequestBody DeleteHiloRequest request){
        return hiloService.eliminarHilo(request);
    }

    @PostMapping("/cerrar/")
    public HiloSerializer cerrarHilo(@Valid @RequestBody DeleteHiloRequest request){ //Uso la clase DeleteHiloRequest para reutilizar su unico atributo que tiene
        return hiloService.cerrarHilo(request);
    }

    @PostMapping("/mover/")
    public HiloSerializer moverHilo(@Valid @RequestBody MoveHiloRequest request){
        return hiloService.moverHilo(request);
    }
}
