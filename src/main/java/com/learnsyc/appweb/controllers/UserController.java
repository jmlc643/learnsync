package com.learnsyc.appweb.controllers;

import java.util.List;

import com.learnsyc.appweb.serializers.usuario.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.services.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired UserService userService;

    @GetMapping("/listar/")
    public List<UserSerializer> listarUsuario() {
        return userService.listarUsuarios().stream().map((it) -> new UserSerializer(it.getUser(), it.getEmail(), it.getNroPuntos())).toList();
    }

    @PostMapping("/register/")
    public Usuario crearUsuario(@Valid @RequestBody SaveUserRequest request) {
        Usuario usuario = new Usuario(null, request.getUser(), request.getPassword(), request.getEmail());
        return userService.guardarUsuario(usuario);
    }

    @PostMapping("/recuperar-contra/")
    public String recuperarContraseña(@Valid @RequestBody RecuperarContraRequest request){
        return userService.recuperarContraseña(request);
    }

    @PostMapping("/authentication/")
    public AuthenticationUserResponse iniciarSesion(@Valid @RequestBody AuthenticationUserRequest request) throws Exception {
        return userService.autenticarUsuario(request);
    }

    @PostMapping("/suspender/")
    public Usuario suspenderUsuario(@Valid @RequestBody SuspendedUserRequest request){
        return userService.suspenderUsuario(request);
    }

    @PostMapping("/banear/")
    public Usuario banearUsuario(@Valid @RequestBody BanUserRequest request){
        return userService.banearUsuario(request);
    }

    @PostMapping("/desbanear/")
    public Usuario desbanearUsuario(@Valid @RequestBody BanUserRequest request){
        return userService.desbanearUsuario(request);
    }
}
