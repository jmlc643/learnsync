package com.learnsyc.appweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/iniciar-sesion")
    public AuthenticationUserResponse iniciarSesion(@RequestBody AuthenticationUserRequest request) throws Exception {
        return autenticacionServices.autenticarUsuario(request);
    }
}