package com.learnsyc.appweb;

import com.learnsyc.appweb.models.Comentario;
import com.learnsyc.appweb.models.Hilo;
import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.repositories.ComentarioRepository;
import com.learnsyc.appweb.services.ComentarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComentarioServiceTest {

    @Mock
    ComentarioRepository comentarioRepository;

    @InjectMocks
    ComentarioService comentarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarComentario() {
        // Given
        Comentario comentario1 = new Comentario(1L, "Mensaje 1", new Hilo(), new Usuario());
        Comentario comentario2 = new Comentario(2L, "Mensaje 2", new Hilo(), new Usuario());
        List<Comentario> comentarios = Arrays.asList(comentario1, comentario2);

        when(comentarioRepository.findAll()).thenReturn(comentarios);

        // When
        List<Comentario> result = comentarioService.listarComentario();

        // Then
        assertEquals(2, result.size());
        assertEquals("Mensaje 1", result.get(0).getMensaje());
        assertEquals("Mensaje 2", result.get(1).getMensaje());
    }
}