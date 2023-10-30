package com.learnsyc.appweb.repositories;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.learnsyc.appweb.models.Usuario;

public class UserRepositoryArray implements IUserRepository {
    
    private static ArrayList<Usuario> usuariosList = new ArrayList<>();
    
    @Override
    public ArrayList<Usuario> listarUsuarios() {
        return usuariosList;
    }
    
    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        usuariosList.add(usuario);
        return usuario;
    }
}
