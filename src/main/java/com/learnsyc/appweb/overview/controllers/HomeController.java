package com.learnsyc.appweb.overview.controllers;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learnsyc.appweb.overview.Usuario;

@RestController
public class HomeController {

    static ArrayList<Usuario> array = new ArrayList<>();

    @GetMapping("/")
    String home(){
        return "Hello World";
    }

    @GetMapping("/usuarios")
    private ArrayList<Usuario> listarUsuario() {
        array.stream().map(usuario -> usuario.getNombre().toUpperCase()).forEach(nombre ->{
            System.out.println("-" + nombre);
        });
        return array;
    }

    @PostMapping("/usuarios")
    private ArrayList<Usuario> crearUsuario(@RequestBody Usuario usuario) throws IOException {
        System.out.println("Ingrese su nombre");
        System.out.println("Ingrese su usuario");
        System.out.println("Ingrese su password");
        array.add(usuario);
        return array;
    }
}
