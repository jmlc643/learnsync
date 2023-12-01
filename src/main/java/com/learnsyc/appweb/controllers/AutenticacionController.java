package com.learnsyc.appweb.controllers;

import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.serializers.usuario.RecuperarContraRequest;
import com.learnsyc.appweb.serializers.usuario.SaveUserRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnsyc.appweb.services.AutenticacionServices;
import com.learnsyc.appweb.serializers.usuario.AuthenticationUserRequest;
import com.learnsyc.appweb.serializers.usuario.AuthenticationUserResponse;

@RestController
@RequestMapping("autenticacion")
public class AutenticacionController {

    @Autowired
    private AutenticacionServices autenticacionServices;

    @PostMapping("/register/")
    public Usuario crearUsuario(@Valid @RequestBody SaveUserRequest request) {
        Usuario usuario = new Usuario(null, request.getUser(), request.getPassword(), request.getEmail());
        return autenticacionServices.guardarUsuario(usuario);
    }

    @PostMapping("/recuperar-contra/")
    public String recuperarContraseña(@Valid @RequestBody RecuperarContraRequest request){
        return autenticacionServices.recuperarContraseña(request);
    }

    @PostMapping("/authentication/")
    public ResponseEntity<AuthenticationUserResponse> iniciarSesion(@Valid @RequestBody AuthenticationUserRequest request) throws Exception {
        return ResponseEntity.ok(autenticacionServices.autenticarUsuario(request));
    }
}