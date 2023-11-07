package com.learnsyc.appweb.services;

import java.time.LocalDateTime;
import java.util.List;

import com.learnsyc.appweb.serializers.usuario.*;
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

    public Usuario guardarUsuario(SaveUserRequest request) {
        //enviar correo
        Usuario usuario = new Usuario(null, request.getUser(), request.getPassword(), request.getEmail());
        return userRepository.save(usuario);
    }

    public Usuario encontrarUsuario(String user) {
        return userRepository.findByUser(user);
    }

    public UserSerializer autenticarUsuario(AuthenticationUserRequest request){
        try{
            Usuario usuario = userRepository.findByUserAndPassword(request.getUser(), request.getPassword());
            return new UserSerializer(usuario.getUser(), usuario.getEmail());
        }catch (Exception e){
            System.out.println("Usuario no encontrado");
            return new UserSerializer("","");
        }
    }

    public Usuario guardarCambios(Usuario usuario){return userRepository.saveAndFlush(usuario);}

    public Usuario suspenderUsuario(SuspendedUserRequest request){
        Usuario usuario = encontrarUsuario(request.getUser());
        usuario.setBaneado(true);
        usuario.setInicioSuspension(LocalDateTime.now());
        usuario.setFinSuspension(request.getFinSuspension());
        return guardarCambios(usuario);
    }

    public Usuario banearUsuario(BanUserRequest request){
        Usuario usuario = encontrarUsuario(request.getUser());
        usuario.setBaneado(true);
        return guardarCambios(usuario);
    }

    public Usuario desbanearUsuario(BanUserRequest request){
        Usuario usuario = encontrarUsuario(request.getUser());
        usuario.setBaneado(false);
        return guardarCambios(usuario);
    }
}