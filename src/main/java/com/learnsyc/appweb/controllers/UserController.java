package com.learnsyc.appweb.controllers;

import java.util.List;

import com.learnsyc.appweb.serializers.usuario.AuthenticationUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.serializers.usuario.SaveUserRequest;
import com.learnsyc.appweb.serializers.usuario.SaveUserResponse;
import com.learnsyc.appweb.serializers.usuario.UserSerializer;
import com.learnsyc.appweb.services.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired UserService userService; 

    @GetMapping("/")
    public List<UserSerializer> listarUsuario() {
        return userService.listarUsuarios().stream().map((it) -> new UserSerializer(it.getUser(), it.getEmail())).toList();
    }

    @PostMapping("/")
    public SaveUserResponse crearUsuario(@RequestBody SaveUserRequest request) {
        Usuario usuario = new Usuario(null, request.getUser(), request.getPassword(), request.getEmail());
        userService.guardarUsuario(usuario);
        return new SaveUserResponse("Guardado!");
    }

    @PostMapping("/authentication")
    public UserSerializer iniciarSesion(@RequestBody AuthenticationUserRequest request){
        try{
            Usuario usuario = userService.autenticarUsuario(request.getUser(), request.getPassword());
            UserSerializer usuarioEncontrado = new UserSerializer(usuario.getUser(), usuario.getEmail());
            return usuarioEncontrado;
        }catch (Exception e){
            System.out.println("Usuario no encontrado");
            return new UserSerializer("","");
        }
    }
}
