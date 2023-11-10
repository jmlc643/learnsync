package com.learnsyc.appweb;

import com.learnsyc.appweb.excepciones.ResourceAlreadyExistsException;
import com.learnsyc.appweb.excepciones.ResourceNotExistsException;
import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.repositories.UserRepository;
import com.learnsyc.appweb.serializers.usuario.AuthenticationUserRequest;
import com.learnsyc.appweb.serializers.usuario.SaveUserRequest;
import com.learnsyc.appweb.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarUsuario() {
        // Given
        Usuario usuario1 = new Usuario(1L, "User1", "Password1", "Email1@gmail.com");
        Usuario usuario2 = new Usuario(2L, "User2", "Password2", "Email2@gmail.com");
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);
        when(userRepository.findAll()).thenReturn(usuarios);

        // When
        List<Usuario> result = userService.listarUsuarios();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("User1", result.get(0).getUser());
        assertEquals("Password1", result.get(0).getPassword());
        assertEquals("Email1@gmail.com", result.get(0).getEmail());
        assertEquals("User2", result.get(1).getUser());
        assertEquals("Password2", result.get(1).getPassword());
        assertEquals("Email2@gmail.com", result.get(1).getEmail());
    }

    @Test
    public void testGuardarUsuario() {
        //Given
        Usuario usuarioMock = new Usuario(1L, "User1", "Password1", "Email1@gmail.com");
        when(userRepository.existsUsuarioByUser(usuarioMock.getUser())).thenReturn(false);
        when(userRepository.existsUsuarioByEmail(usuarioMock.getEmail())).thenReturn(false);
        when(userRepository.save(usuarioMock)).thenReturn(usuarioMock);
        //When
        Usuario usuarioAGuardar = new Usuario(1L, "User1", "Password1", "Email1@gmail.com");
        Usuario usuarioGuardado;
        usuarioGuardado = userService.guardarUsuario(usuarioAGuardar);
        //Then
        assertNotNull(usuarioGuardado);
        assertEquals(1L, usuarioGuardado.getIdUsuario());
        assertEquals("User1", usuarioGuardado.getUser());
        assertEquals("Password1", usuarioGuardado.getPassword());
        assertEquals("Email1@gmail.com", usuarioGuardado.getEmail());
    }

    @Test
    public void testGuardarUsuario_UserExistente() {
        Usuario user1 = new Usuario(1L, "User1", "Password1", "Email1@gmail.com");
        Usuario userGuardado1 = userService.guardarUsuario(user1);
        Usuario user2 = new Usuario(2L, "User1", "Password2", "Email2@gmail.com");
        Usuario userGuardado2;
        try {
            userGuardado2 = userService.guardarUsuario(user2);
        } catch (ResourceAlreadyExistsException e) {
            assertEquals(userGuardado1.getUser(), user2.getUser());
            assertEquals("El usuario " + user2.getUser() + " existe", e.getMessage());
        }
    }

    @Test
    public void testGuardarUsuario_EmailExistente() {
        Usuario user1 = new Usuario(1L, "User1", "Password1", "Email1@gmail.com");
        Usuario userGuardado1 = userService.guardarUsuario(user1);
        Usuario user2 = new Usuario(2L, "User2", "Password2", "Email1@gmail.com");
        Usuario userGuardado2;
        try {
            userGuardado2 = userService.guardarUsuario(user2);
        } catch (ResourceAlreadyExistsException e) {
            assertEquals(userGuardado1.getUser(), user2.getUser());
            assertEquals("El email ya ha sido usado para la creación de otro usuario", e.getMessage());
        }
    }

    @Test
    public void testEncontrarUsuario() {
        // Given
        SaveUserRequest saveUserRequest = new SaveUserRequest("john.doe", "password123", "john.doe@example.com");
        Usuario userToFind = new Usuario(1L, saveUserRequest.getUser(), saveUserRequest.getPassword(), saveUserRequest.getEmail());
        when(userRepository.existsUsuarioByUser(userToFind.getUser())).thenReturn(true);
        when(userRepository.findByUser("john.doe")).thenReturn(userToFind);

        // When
        Usuario result = userService.encontrarUsuario("john.doe");

        // Then
        assertNotNull(result);
        assertUsuarioEquals(userToFind, result);
    }

    @Test
    public void testEncontrarUsuario_UsuarioNoExiste(){
        Usuario user;
        try{
            user = userService.encontrarUsuario("john.doe");
        }catch (ResourceNotExistsException e){
            assertEquals("El usuario john.doe no existe", e.getMessage());
        }
    }

    @Test
    public void testAutenticarUsuario(){
        //Give
        Usuario user = new Usuario(1L, "User1", "Password1", "Email1@gmail.com");
        when(userRepository.existsUsuarioByUserAndPassword("User1", "Password1")).thenReturn(true);
        when(userRepository.findByUserAndPassword("User1", "Password1")).thenReturn(user);
        //When
        AuthenticationUserRequest authenticationUserRequest = new AuthenticationUserRequest("User1", "Password1");
        Usuario authenticatedUser = userService.autenticarUsuario(authenticationUserRequest);
        //Then
        assertNotNull(authenticatedUser);
        assertUsuarioEquals(authenticatedUser, user);
    }

    @Test
    public void testAutenticarUsuario_UsuarioNoEncontrado(){
        Usuario usuario;
        AuthenticationUserRequest authenticationUserRequest = new AuthenticationUserRequest("User1", "Password1");
        try{
            usuario = userService.autenticarUsuario(authenticationUserRequest);
        }catch (ResourceNotExistsException e){
            assertEquals("El usuario o contraseña son incorrectos", e.getMessage());
        }
    }

    @Test
    public void testSuspenderUsuario() {
        // Given
        Usuario userToSuspend = new Usuario(1L, "john.doe", "password123", "john.doe@example.com");
        LocalDateTime finSuspension = LocalDateTime.now().plusDays(7);
        userToSuspend.setBaneado(true);
        userToSuspend.setInicioSuspension(LocalDateTime.now());
        userToSuspend.setFinSuspension(finSuspension);
        userService.guardarCambios(userToSuspend);
        verify(userRepository, times(1)).saveAndFlush(userToSuspend);
    }

    @Test
    public void testBanearUsuario() {
       Usuario usuario = new Usuario(1L, "User1", "Password1", "Email1@gmail.com");
       usuario.setBaneado(true);
       userService.guardarCambios(usuario);
       verify(userRepository, times(1)).saveAndFlush(usuario);
    }

    @Test
    public void testDesbanearUsuario() {
        Usuario usuario = new Usuario(1L, "User1", "Password1", "Email1@gmail.com");
        usuario.setBaneado(false);
        userService.guardarCambios(usuario);
        verify(userRepository, times(1)).saveAndFlush(usuario);
    }

    private void assertUsuarioEquals(Usuario expected, Usuario actual) {
        assertEquals(expected.getUser(), actual.getUser());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getEmail(), actual.getEmail());
    }
}