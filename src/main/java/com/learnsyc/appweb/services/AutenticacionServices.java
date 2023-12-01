package com.learnsyc.appweb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.learnsyc.appweb.serializers.usuario.AuthenticationUserRequest;
import com.learnsyc.appweb.serializers.usuario.AuthenticationUserResponse;
import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.util.EncryptionUtil;

import java.util.Optional;

@Service
public class AutenticacionServices {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationUserResponse autenticarUsuario(AuthenticationUserRequest request) throws Exception {
        Optional<Usuario> usuario = Optional.ofNullable(userService.encontrarUsuarioPorUser(request.getUser()));
        if (usuario.isPresent()) {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUser(), request.getPassword()));
                return new AuthenticationUserResponse(EncryptionUtil.encrypt(userService.generarToken(usuario.get())));
            } catch (AuthenticationException e) {
                // Pasar la excepción a la parte correspondiente.
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario y/o contraseña incorrectos", e);
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario y/o contraseña incorrectos");
    }
}
