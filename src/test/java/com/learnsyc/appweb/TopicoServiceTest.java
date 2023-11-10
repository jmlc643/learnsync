package com.learnsyc.appweb;

import com.learnsyc.appweb.excepciones.ResourceAlreadyExistsException;
import com.learnsyc.appweb.excepciones.ResourceNotExistsException;
import com.learnsyc.appweb.models.Categoria;
import com.learnsyc.appweb.models.Topico;
import com.learnsyc.appweb.repositories.TopicoRepository;
import com.learnsyc.appweb.services.TopicoService;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
        Categoria categoria = new Categoria(1L, "Categoria1", "Descripcion1");
        Topico topico1 = new Topico(1L, "Tópico 1", "Descripción 1", categoria);
        Topico topico2 = new Topico(2L, "Tópico 2", "Descripción 2", categoria);
        List<Topico> topicos = Arrays.asList(topico1, topico2);

        when(topicoRepository.findAll()).thenReturn(topicos);

        // When
        List<Topico> result = topicoService.listarTopico();

        // Then
        assertNotNull(topicos);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getIdTopico());
        assertEquals("Tópico 1", result.get(0).getNombre());
        assertEquals("Descripción 1", result.get(0).getDescripcion());
        assertEquals(2L, result.get(1).getIdTopico());
        assertEquals("Tópico 2", result.get(1).getNombre());
        assertEquals("Descripción 2", result.get(1).getDescripcion());
        assertNotNull(result.get(0).getCategoria());
        assertNotNull(result.get(1).getCategoria());
        assertEquals(1L, result.get(0).getCategoria().getIdCategorias());
        assertEquals("Categoria1", result.get(0).getCategoria().getNombre());
        assertEquals("Descripcion1", result.get(0).getCategoria().getDescripcion());
        assertEquals(1L, result.get(1).getCategoria().getIdCategorias());
        assertEquals("Categoria1", result.get(1).getCategoria().getNombre());
        assertEquals("Descripcion1", result.get(1).getCategoria().getDescripcion());
    }

    @Test
    public void testGuardarTopico(){
        //Give
        Categoria categoriaMock = new Categoria(1L, "Categoria1", "Descripcion1");
        Topico topico = new Topico(1L, "Tópico 1", "Descripción 1", categoriaMock);
        when(topicoRepository.existsTopicoByNombre(topico.getNombre())).thenReturn(false);
        when(topicoRepository.save(topico)).thenReturn(topico);
        //When
        Categoria categoria = new Categoria(1L, "Categoria1", "Descripcion1");
        Topico topicoAGuardar = new Topico(1L, "Tópico 1", "Descripción 1", categoria);
        Topico topicoGuardado;
        topicoGuardado = topicoService.guardarTopico(topicoAGuardar);
        //Then
        assertNotNull(topicoGuardado);
        assertEquals(1L, topicoGuardado.getIdTopico());
        assertEquals("Tópico 1", topicoGuardado.getNombre());
        assertEquals("Descripción 1", topicoGuardado.getDescripcion());
        assertEquals(1L, topicoGuardado.getCategoria().getIdCategorias());
        assertEquals("Categoria1", topicoGuardado.getCategoria().getNombre());
        assertEquals("Descripcion1", topicoGuardado.getCategoria().getDescripcion());
    }

    @Test
    public void testGuardarTopico_TopicoExistente(){
        Categoria categoriaMock = new Categoria(1L, "Categoria1", "Descripcion1");
        Topico topico1 = new Topico(1L, "Tópico 1", "Descripción 1", categoriaMock);
        Topico topicoGuardado1;
        topicoGuardado1 = topicoService.guardarTopico(topico1);
        Topico topico2 = new Topico(2L, "Tópico 1", "Descripción 2", categoriaMock);
        Topico topicoGuardado2;
        try{
            topicoGuardado2 = topicoService.guardarTopico(topico2);
        }catch (ResourceAlreadyExistsException e){
            assertEquals(topicoGuardado1.getNombre(), topico2.getNombre());
            assertEquals(e.getMessage(), "El tópico "+topico2.getNombre()+" existe");
        }
    }

    @Test
    public void testBuscarTopico(){
        //Give
        Categoria categoriaMock = new Categoria(1L, "Categoria1", "Descripcion1");
        Topico topico = new Topico(1L, "Tópico 1", "Descripción 1", categoriaMock);
        when(topicoRepository.existsTopicoByNombre("Tópico 1")).thenReturn(true);
        when(topicoRepository.findByNombre("Tópico 1")).thenReturn(topico);
        //When
        Topico topicoEncontrado = topicoService.buscarTopico("Tópico 1");
        //Then
        assertNotNull(topicoEncontrado);
        assertEquals(1L, topicoEncontrado.getIdTopico());
        assertEquals("Tópico 1", topicoEncontrado.getNombre());
        assertEquals("Descripción 1", topicoEncontrado.getDescripcion());
        assertNotNull(topicoEncontrado.getCategoria());
        assertEquals(1L, topicoEncontrado.getCategoria().getIdCategorias());
        assertEquals("Categoria1", topicoEncontrado.getCategoria().getNombre());
        assertEquals("Descripcion1", topicoEncontrado.getCategoria().getDescripcion());
    }

    @Test
    public void testBuscarTopico_TopicoNoExiste(){
        Topico topico;
        try{
            topico = topicoService.buscarTopico("Nombre 1");
        }catch (ResourceNotExistsException e2){
            assertEquals("El tópico Nombre 1 no existe", e2.getMessage());
        }
    }

    @Test
    public void testEditarCambios(){
        //Give
        Categoria categoriaMock = new Categoria(1L, "Nombre1", "Descripcion1");
        Topico topicoMock = new Topico(1L, "Nombre1", "Descripcion1", categoriaMock);
        topicoMock.setNombre("Nombre 1");
        topicoMock.setDescripcion("Descripcion 1");
        topicoService.guardarCambios(topicoMock);
        verify(topicoRepository, times(1)).saveAndFlush(topicoMock);
    }

    @Test
    public void testEliminarTopico(){
        Categoria categoriaMock = new Categoria(1L, "Nombre1", "Descripcion1");
        Topico topicoMock = new Topico(1L, "Nombre1", "Descripcion1", categoriaMock);
        topicoRepository.deleteById(1L);
        verify(topicoRepository, times(1)).deleteById(1L);
    }
}