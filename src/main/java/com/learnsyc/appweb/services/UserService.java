package com.learnsyc.appweb.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.learnsyc.appweb.excepciones.*;
import com.learnsyc.appweb.models.ConfirmationToken;
import com.learnsyc.appweb.repositories.ConfirmationTokenRepository;
import com.learnsyc.appweb.serializers.usuario.*;
import com.learnsyc.appweb.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
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
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired EmailService emailService;


    public List<Usuario> listarUsuarios() {
        return userRepository.findAll();
    }

    public Usuario guardarUsuario(Usuario usuario) {
        if(userRepository.existsUsuarioByUser(usuario.getUser())){
            throw new ResourceAlreadyExistsException("El usuario "+usuario.getUser()+" existe");
        }
        if(userRepository.existsUsuarioByEmail(usuario.getEmail())){
            throw new ResourceAlreadyExistsException("El email ya ha sido usado para la creación de otro usuario");
        }/*
        String token = generarToken(usuario);
        enviarEmail(token);
        */
        usuario.setEnable(true);
        return userRepository.save(usuario);
    }

    private void enviarEmail(String token) {
        String url = "localhost:8080/user/confirmation-token?"+token;

    }

    public ConfirmationToken encontrarToken(String token){
        if(!confirmationTokenRepository.existsConfirmationTokenByToken(token)){
            throw new ResourceNotExistsException("Token no válido");
        }
        return confirmationTokenRepository.findByToken(token);
    }

    public void ConfirmarCuenta(String token){
        ConfirmationToken confirmationToken = encontrarToken(token);
        if(confirmationToken.getFechaActivacion() != null){
            throw new EmailConfirmedException("Este email ya ha sido confirmado");
        }
        LocalDateTime fechaExpiracion = confirmationToken.getFechaExpiracion();
        if(fechaExpiracion.isBefore(LocalDateTime.now())){
            throw new ExpiredToken("Token expirado");
        }
        confirmationToken.setFechaActivacion(LocalDateTime.now());
        Usuario usuario = confirmationToken.getUsuario();
        usuario.setEnable(true);
        guardarCambios(usuario);
    }

    public String recuperarContraseña(RecuperarContraRequest request){
        Usuario usuario = encontrarUsuarioPorEmail(request.getEmail());
        String mensaje = "Hola "+usuario.getUser()+"\nTú has solicitado la recuperación de tu contraseña y Learnsync se encarga de ayudar a sus usuarios en problemas como este." +
                "\nSu contraseña es "+usuario.getPassword()+", ahora podrá autenticarse en Learnsync para hacer uso de sus funcionalidades.";
        emailService.sendEmail(request.getEmail(), "Recuperar contraseña", mensaje);
        return "Correo enviado, revise su bandeja de entrada";
    }

    public String generarToken(Usuario usuario){
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(null, token, usuario);
        confirmationTokenRepository.save(confirmationToken);
        return token;
    }

    public Usuario encontrarUsuarioPorUser(String user) {
        if(!userRepository.existsUsuarioByUser(user)){
            throw new ResourceNotExistsException("El usuario "+user+" no existe");
        }
        return userRepository.findByUser(user);
    }

    public Usuario encontrarUsuarioPorEmail(String email){
        if(!userRepository.existsUsuarioByEmail(email)){
            throw new ResourceNotExistsException(("Usuario no encontrado"));
        }
        return userRepository.findByEmail(email);
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
        Usuario usuario = encontrarUsuarioPorUser(request.getUser());
        usuario.setBaneado(true);
        usuario.setInicioSuspension(LocalDateTime.now());
        usuario.setFinSuspension(request.getFinSuspension());
        return guardarCambios(usuario);
    }

    public Usuario banearUsuario(BanUserRequest request){
        Usuario usuario = encontrarUsuarioPorUser(request.getUser());
        usuario.setBaneado(true);
        return guardarCambios(usuario);
    }

    public Usuario desbanearUsuario(BanUserRequest request){
        Usuario usuario = encontrarUsuarioPorUser(request.getUser());
        usuario.setBaneado(false);
        return guardarCambios(usuario);
    }

    public UserSerializer retornarUsuario(Usuario usuario){
        return new UserSerializer(usuario.getUser(), usuario.getEmail(), usuario.getNroPuntos());
    }
}