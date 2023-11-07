package com.learnsyc.appweb;

import com.learnsyc.appweb.models.Categoria;
import com.learnsyc.appweb.repositories.CategoriaRepository;
import com.learnsyc.appweb.services.CategoriaService;
import com.learnsyc.appweb.serializers.categoria.CategoriaSerializer;
import com.learnsyc.appweb.serializers.categoria.DeleteCategoriaRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CategoriaServiceTest {

    @Mock
    CategoriaRepository categoriaRepository;

    @InjectMocks
    CategoriaService categoriaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarCategorias() {
        // Given
        Categoria categoria1 = new Categoria(1L, "Nombre 1", "Descripción 1");
        Categoria categoria2 = new Categoria(2L, "Nombre 2", "Descripción 2");
        List<Categoria> categorias = Arrays.asList(categoria1, categoria2);

        when(categoriaRepository.findAll()).thenReturn(categorias);

        // When
        List<Categoria> result = categoriaService.listarCategorias();

        // Then
        assertEquals(2, result.size());
        assertEquals("Nombre 1", result.get(0).getNombre());
        assertEquals("Descripción 1", result.get(0).getDescripcion());
        assertEquals("Nombre 2", result.get(1).getNombre());
        assertEquals("Descripción 2", result.get(1).getDescripcion());
    }
}