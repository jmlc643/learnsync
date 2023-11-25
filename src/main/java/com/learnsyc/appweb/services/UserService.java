package com.learnsyc.appweb.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.learnsyc.appweb.excepciones.BannedUserException;
import com.learnsyc.appweb.excepciones.ResourceAlreadyExistsException;
import com.learnsyc.appweb.excepciones.ResourceNotExistsException;
import com.learnsyc.appweb.serializers.usuario.*;
import com.learnsyc.appweb.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.repositories.UserRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired JwtTokenUtil jwtTokenUtil;
    @Autowired
    AuthenticationManager authenticationManager;


    public List<Usuario> listarUsuarios() {
        return userRepository.findAll();
    }

    public Usuario guardarUsuario(Usuario usuario) {
        //enviar correo
        if(userRepository.existsUsuarioByUser(usuario.getUser())){
            throw new ResourceAlreadyExistsException("El usuario "+usuario.getUser()+" existe");
        }
        if(userRepository.existsUsuarioByEmail(usuario.getEmail())){
            throw new ResourceAlreadyExistsException("El email ya ha sido usado para la creación de otro usuario");
        }
        return userRepository.save(usuario);
    }

    public Usuario encontrarUsuario(String user) {
        if(!userRepository.existsUsuarioByUser(user)){
            throw new ResourceNotExistsException("El usuario "+user+" no existe");
        }
        return userRepository.findByUser(user);
    }

    public AuthenticationUserResponse autenticarUsuario(AuthenticationUserRequest request) throws Exception {
        Optional<Usuario> usuario = userRepository.findByUserAndPassword(request.getUser(), request.getPassword());
        if(usuario.isPresent()){
            try{
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUser(), request.getPassword()));
                return new AuthenticationUserResponse(jwtTokenUtil.generateToken(usuario.get()));
            }catch (Exception e){}
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario y/o password incorrectos");
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

    public UserSerializer retornarUsuario(Usuario usuario){
        return new UserSerializer(usuario.getUser(), usuario.getEmail(), usuario.getNroPuntos());
    }
}