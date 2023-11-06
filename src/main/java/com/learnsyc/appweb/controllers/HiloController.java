package com.learnsyc.appweb.controllers;

import com.learnsyc.appweb.models.Hilo;
import com.learnsyc.appweb.models.Topico;
import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.serializers.hilos.DeleteHiloRequest;
import com.learnsyc.appweb.serializers.hilos.HiloSerializer;
import com.learnsyc.appweb.serializers.hilos.MoveHiloRequest;
import com.learnsyc.appweb.serializers.hilos.SaveHiloRequest;
import com.learnsyc.appweb.services.HiloService;
import com.learnsyc.appweb.services.TopicoService;
import com.learnsyc.appweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hilo")
//@CrossOrigin(origins = "http://localhost:4200")
public class HiloController {
    @Autowired TopicoService topicoService; 
    @Autowired UserService userService;
    @Autowired HiloService hiloService;

    @GetMapping("/")
    public List<HiloSerializer> listarHilo() {
        return hiloService.listarHilo().stream().map((it) -> hiloService.retornarHilo(it)).toList();
    }

    @PostMapping("/")
    public Hilo crearHilo(@RequestBody SaveHiloRequest request) {
        Usuario usuario = userService.encontrarUsuario(request.getUsername());
        Topico topico = topicoService.encontrarTopico(request.getTopicname());
        Hilo hilo = new Hilo(null, request.getTitulo(), request.getMensaje(), topico, usuario);
        hiloService.guardarHilo(hilo);
        return hilo;
    }

    @DeleteMapping("/")
    public Hilo eliminarHilo(@RequestBody DeleteHiloRequest request){
        Hilo hilo = hiloService.encontrarHilo(request.getId());
        hiloService.eliminarHilo(request.getId());
        return hilo;
    }

    @PostMapping("/cerrar/")
    public HiloSerializer cerrarHilo(@RequestBody DeleteHiloRequest request){ //Uso la clase DeleteHiloRequest para reutilizar su unico atributo que tiene
        Hilo hilo = hiloService.encontrarHilo(request.getId());
        hilo.setCerrado(true);
        hiloService.guardarCambios(hilo);
        return hiloService.retornarHilo(hilo);
    }

    @PostMapping("/mover/")
    public HiloSerializer moverHilo(@RequestBody MoveHiloRequest request){
        Hilo hilo = hiloService.encontrarHilo(request.getId());
        Topico topico = topicoService.encontrarTopico(request.getNombreTopico());
        hilo.setTopico(topico);
        hiloService.guardarCambios(hilo);
        return hiloService.retornarHilo(hilo);
    }
}
