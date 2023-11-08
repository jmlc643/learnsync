package com.learnsyc.appweb;

import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.repositories.UserRepository;
import com.learnsyc.appweb.serializers.usuario.SaveUserRequest;
import com.learnsyc.appweb.serializers.usuario.SuspendedUserRequest; // Importación de SuspendedUserRequest
import com.learnsyc.appweb.serializers.usuario.BanUserRequest; // Importación de BanUserRequest
import com.learnsyc.appweb.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testGuardarUsuario() {
        // Given
        SaveUserRequest saveUserRequest = new SaveUserRequest("john.doe", "password123", "john.doe@example.com");
        Usuario usuarioToSave = new Usuario(null, saveUserRequest.getUser(), saveUserRequest.getPassword(), saveUserRequest.getEmail());
        Usuario usuarioSaved = new Usuario(1L, saveUserRequest.getUser(), saveUserRequest.getPassword(), saveUserRequest.getEmail());

        when(userRepository.save(usuarioToSave)).thenReturn(usuarioSaved);

        // When
        Usuario result = userService.guardarUsuario(saveUserRequest);

        // Then
        assertUsuarioEquals(usuarioSaved, result);
    }

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