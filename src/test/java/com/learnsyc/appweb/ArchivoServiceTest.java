package com.learnsyc.appweb;

import com.learnsyc.appweb.models.Archivo;
import com.learnsyc.appweb.models.Comentario;
import com.learnsyc.appweb.repositories.ArchivoRepository;
import com.learnsyc.appweb.services.ArchivoService;
import com.learnsyc.appweb.services.ComentarioService;
import com.learnsyc.appweb.serializers.archivo.ArchivoSerializer;
import com.learnsyc.appweb.serializers.archivo.SaveArchivoRequest;
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
public class ArchivoServiceTest {

    @Mock
    ArchivoRepository archivoRepository;

    @Mock
    ComentarioService comentarioService;

    @InjectMocks
    ArchivoService archivoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarArchivo() {
        // Given
        Comentario comentario = new Comentario(1L, "Mensaje", null, null);
        Archivo archivo1 = new Archivo(1L, "Archivo 1", "Tipo 1", "Link 1", comentario);
        Archivo archivo2 = new Archivo(2L, "Archivo 2", "Tipo 2", "Link 2", comentario);
        List<Archivo> archivos = Arrays.asList(archivo1, archivo2);

        when(archivoRepository.findAll()).thenReturn(archivos);

        // When
        List<Archivo> result = archivoService.listarArchivo();

        // Then
        assertEquals(2, result.size());
        assertEquals("Archivo 1", result.get(0).getNombre());
        assertEquals("Tipo 1", result.get(0).getTipo());
        assertEquals("Link 1", result.get(0).getLink());
        assertEquals("Archivo 2", result.get(1).getNombre());
        assertEquals("Tipo 2", result.get(1).getTipo());
        assertEquals("Link 2", result.get(1).getLink());
    }
}