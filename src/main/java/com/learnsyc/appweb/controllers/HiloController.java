package com.learnsyc.appweb.controllers;

import com.learnsyc.appweb.models.Hilo;
import com.learnsyc.appweb.models.Topico;
import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.serializers.categoria.CategoriaSerializer;
import com.learnsyc.appweb.serializers.hilos.HiloSerializer;
import com.learnsyc.appweb.serializers.hilos.SaveHiloRequest;
import com.learnsyc.appweb.serializers.topico.TopicoSerializer;
import com.learnsyc.appweb.serializers.usuario.UserSerializer;
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
        return hiloService.listarHilo().stream().map((it) -> new HiloSerializer(it.getIdHilo(), it.getTitulo(), it.getMensaje(),
                new TopicoSerializer(it.getTopico().getNombre(), it.getTopico().getDescripcion(),
                new CategoriaSerializer(it.getTopico().getCategoria().getNombre(), it.getTopico().getCategoria().getDescripcion())),
                new UserSerializer(it.getUsuario().getUser(), it.getUsuario().getEmail()))).toList();
    }

    @PostMapping("/")
    public Hilo crearHilo(@RequestBody SaveHiloRequest request) {
        Usuario usuario = userService.encontrarUsuario(request.getUsername());
        Topico topico = topicoService.encontrarTopico(request.getTopicname());
        Hilo hilo = new Hilo(null, request.getTitulo(), request.getMensaje(), topico, usuario);
        hiloService.guardarHilo(hilo);
        return hilo;
    }
}
