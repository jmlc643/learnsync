package com.learnsyc.appweb;

import com.learnsyc.appweb.models.Topico;
import com.learnsyc.appweb.repositories.TopicoRepository;
import com.learnsyc.appweb.services.TopicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TopicoServiceTest {

    @Mock
    TopicoRepository topicoRepository;

    @InjectMocks
    TopicoService topicoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarTopico() {
        // Given
        Topico topico1 = new Topico(1L, "Tópico 1", "Descripción 1", null);
        Topico topico2 = new Topico(2L, "Tópico 2", "Descripción 2", null);
        List<Topico> topicos = Arrays.asList(topico1, topico2);

        when(topicoRepository.findAll()).thenReturn(topicos);

        // When
        List<Topico> result = topicoService.listarTopico();

        // Then
        assertEquals(2, result.size());
        assertEquals("Tópico 1", result.get(0).getNombre());
        assertEquals("Descripción 1", result.get(0).getDescripcion());
        assertEquals("Tópico 2", result.get(1).getNombre());
        assertEquals("Descripción 2", result.get(1).getDescripcion());
    }
}