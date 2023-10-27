package com.learnsyc.appweb.repositories;

import java.util.ArrayList;

import com.learnsyc.appweb.models.Usuario;

public interface IUserRepository {

    ArrayList<Usuario> listarUsuarios();

    Usuario guardarUsuario(Usuario usuario);

}