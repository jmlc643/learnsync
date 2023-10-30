package com.learnsyc.appweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.serializers.SaveUserRequest;
import com.learnsyc.appweb.serializers.SaveUserResponse;
import com.learnsyc.appweb.serializers.UserSerializer;
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
        userService.guardarUsuario(new Usuario(request.getUser(), request.getPassword(), request.getEmail()));
        return new SaveUserResponse("Guardado!");
    }
}
