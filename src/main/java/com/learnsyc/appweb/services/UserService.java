package com.learnsyc.appweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<Usuario> listarUsuarios() {
        return userRepository.findAll();
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return userRepository.save(usuario);
    }
}