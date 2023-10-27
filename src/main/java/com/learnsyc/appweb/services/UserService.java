package com.learnsyc.appweb.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.repositories.IUserRepository;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    public ArrayList<Usuario> listarUsuarios() {
        return userRepository.listarUsuarios();
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return userRepository.guardarUsuario(usuario);
    }
}