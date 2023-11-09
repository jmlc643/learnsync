package com.learnsyc.appweb;

import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.repositories.UserRepository;
import com.learnsyc.appweb.serializers.usuario.SaveUserRequest;
import com.learnsyc.appweb.serializers.usuario.SuspendedUserRequest; // Importación de SuspendedUserRequest
import com.learnsyc.appweb.serializers.usuario.BanUserRequest; // Importación de BanUserRequest
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
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testGuardarUsuario(){
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

    //Avanzar desde aqui para abajo

    @Test
    public void testEncontrarUsuario() {
        // Given
        SaveUserRequest saveUserRequest = new SaveUserRequest("john.doe", "password123", "john.doe@example.com");
        Usuario userToFind = new Usuario(1L, saveUserRequest.getUser(), saveUserRequest.getPassword(), saveUserRequest.getEmail());

        when(userRepository.findByUser("john.doe")).thenReturn(userToFind);

        // When
        Usuario result = userService.encontrarUsuario("john.doe");

        // Then
        assertUsuarioEquals(userToFind, result);
    }

    @Test
    public void testSuspenderUsuario() {
        // Given
        String username = "john.doe";
        Usuario userToSuspend = new Usuario(1L, username, "password123", "john.doe@example.com");
        LocalDateTime finSuspension = LocalDateTime.now().plusDays(7);

        when(userRepository.findByUser(username)).thenReturn(userToSuspend);

        // When
        Usuario result = userService.suspenderUsuario(new SuspendedUserRequest(username, finSuspension));

        // Then
        assertEquals(true, result.isBaneado());
        assertEquals(finSuspension, result.getFinSuspension());
    }

    @Test
    public void testBanearUsuario() {
        // Given
        String username = "john.doe";
        Usuario userToBan = new Usuario(1L, username, "password123", "john.doe@example.com");

        when(userRepository.findByUser(username)).thenReturn(userToBan);

        // When
        Usuario result = userService.banearUsuario(new BanUserRequest(username));

        // Then
        assertEquals(true, result.isBaneado());
    }

    @Test
    public void testDesbanearUsuario() {
        // Given
        String username = "john.doe";
        Usuario userToUnban = new Usuario(1L, username, "password123", "john.doe@example.com");

        // Simula que el usuario está baneado
        userToUnban.setBaneado(true);

        when(userRepository.findByUser(username)).thenReturn(userToUnban);

        // When
        Usuario result = userService.desbanearUsuario(new BanUserRequest(username));

        // Then
        assertEquals(false, result.isBaneado());
    }

    private void assertUsuarioEquals(Usuario expected, Usuario actual) {
        assertEquals(expected.getUser(), actual.getUser());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getEmail(), actual.getEmail());
    }
}