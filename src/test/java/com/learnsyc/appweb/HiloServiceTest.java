package com.learnsyc.appweb;

import com.learnsyc.appweb.models.Hilo;
import com.learnsyc.appweb.models.Topico;
import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.repositories.HiloRepository;
import com.learnsyc.appweb.services.HiloService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class HiloServiceTest {

    @Mock
    HiloRepository hiloRepository;

    @InjectMocks
    HiloService hiloService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarHilo() {
        // Given
        Hilo hilo1 = new Hilo(1L, "Título 1", "Mensaje 1", new Topico(), new Usuario());
        Hilo hilo2 = new Hilo(2L, "Título 2", "Mensaje 2", new Topico(), new Usuario());
        List<Hilo> hilos = Arrays.asList(hilo1, hilo2);

        when(hiloRepository.findAll()).thenReturn(hilos);

        // When
        List<Hilo> result = hiloService.listarHilo();

        // Then
        assertEquals(2, result.size());
        assertEquals("Título 1", result.get(0).getTitulo());
        assertEquals("Mensaje 1", result.get(0).getMensaje());
        assertEquals("Título 2", result.get(1).getTitulo());
        assertEquals("Mensaje 2", result.get(1).getMensaje());
    }
}