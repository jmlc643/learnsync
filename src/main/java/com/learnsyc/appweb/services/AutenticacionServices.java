package com.learnsyc.appweb.services;

import com.learnsyc.appweb.excepciones.EmailConfirmedException;
import com.learnsyc.appweb.excepciones.ExpiredToken;
import com.learnsyc.appweb.excepciones.ResourceAlreadyExistsException;
import com.learnsyc.appweb.excepciones.ResourceNotExistsException;
import com.learnsyc.appweb.models.ConfirmationToken;
import com.learnsyc.appweb.repositories.ConfirmationTokenRepository;
import com.learnsyc.appweb.repositories.UserRepository;
import com.learnsyc.appweb.serializers.usuario.RecuperarContraRequest;
import com.learnsyc.appweb.serializers.usuario.SaveUserRequest;
import com.learnsyc.appweb.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.learnsyc.appweb.serializers.usuario.AuthenticationUserRequest;
import com.learnsyc.appweb.serializers.usuario.AuthenticationUserResponse;
import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.util.EncryptionUtil;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutenticacionServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired EmailService emailService;

    @Autowired TokenService tokenService;

    @Autowired UserService userService;

    public AuthenticationUserResponse registrarUsuario(SaveUserRequest request) {
        Usuario usuario = new Usuario(null, request.getUser(), passwordEncoder.encode(request.getPassword()), request.getEmail());
        if(userRepository.existsUsuarioByUser(usuario.getUser())){
            throw new ResourceAlreadyExistsException("El usuario "+usuario.getUser()+" existe");
        }
        if(userRepository.existsUsuarioByEmail(usuario.getEmail())){
            throw new ResourceAlreadyExistsException("El email ya ha sido usado para la creación de otro usuario");
        }
        usuario.setEnable(true);
        userRepository.save(usuario);
        return new AuthenticationUserResponse(EncryptionUtil.encrypt(jwtTokenUtil.generateToken(usuario)));
    }

    public void ConfirmarCuenta(String token){
        ConfirmationToken confirmationToken = tokenService.encontrarToken(token);
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
        userService.guardarCambios(usuario);
    }

    public String recuperarContraseña(RecuperarContraRequest request){
        Usuario usuario = userService.encontrarUsuarioPorEmail(request.getEmail());
        String mensaje = "Hola "+usuario.getUser()+"\nTú has solicitado la recuperación de tu contraseña y Learnsync se encarga de ayudar a sus usuarios en problemas como este." +
                "\nSu contraseña es "+usuario.getPassword()+", ahora podrá autenticarse en Learnsync para hacer uso de sus funcionalidades.";
        emailService.sendEmail(request.getEmail(), "Recuperar contraseña", mensaje);
        return "Correo enviado, revise su bandeja de entrada";
    }

    public AuthenticationUserResponse autenticarUsuario(AuthenticationUserRequest request) throws Exception {
        Optional<Usuario> usuario = userRepository.findByUser(request.getUser());
        if(usuario.isPresent()){
            try{
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUser(), request.getPassword()));
                return new AuthenticationUserResponse(EncryptionUtil.encrypt(jwtTokenUtil.generateToken(usuario.get())));
            }catch (AuthenticationException e){
                //pass to the throw.
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario y/o password incorrectos");
    }
}
