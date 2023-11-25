package com.learnsyc.appweb;

import com.learnsyc.appweb.models.*;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        Categoria categoriaMock = new Categoria(1L, "Categoria1", "Descripcion1");
        Topico topicoMock = new Topico(1L, "Topico1", "Descripcion1", categoriaMock);
        Usuario usuarioMock = new Usuario(1l, "Usuario1", "Password1", "Email@gmail.com");
        Hilo hiloMock = new Hilo(1L, "Título 1", "Mensaje 1", topicoMock, usuarioMock);
        Comentario comentarioMock = new Comentario(1L, "Mensaje 1", hiloMock, usuarioMock);
        Archivo archivo1 = new Archivo(1L, "Archivo 1", "Tipo 1", "Link 1", comentarioMock);
        Archivo archivo2 = new Archivo(2L, "Archivo 2", "Tipo 2", "Link 2", comentarioMock);
        List<Archivo> archivos = Arrays.asList(archivo1, archivo2);

        when(archivoRepository.findAll()).thenReturn(archivos);

        // When
        List<Archivo> result = archivoService.listarArchivo();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getIdArchivo());
        assertEquals("Archivo 1", result.get(0).getNombre());
        assertEquals("Tipo 1", result.get(0).getTipo());
        assertEquals("Link 1", result.get(0).getLink());
        assertNotNull(result.get(0).getComentario());
        assertEquals(1L, result.get(0).getComentario().getIdComentario());
        assertEquals("Mensaje 1", result.get(0).getComentario().getMensaje());
        assertEquals(1L, result.get(0).getComentario().getHilo().getIdHilo());
        assertEquals("Título 1", result.get(0).getComentario().getHilo().getTitulo());
        assertEquals("Mensaje 1", result.get(0).getComentario().getHilo().getMensaje());
        assertNotNull(result.get(0).getComentario().getHilo().getTopico());
        assertEquals(1L, result.get(0).getComentario().getHilo().getTopico().getIdTopico());
        assertEquals("Topico1", result.get(0).getComentario().getHilo().getTopico().getNombre());
        assertEquals("Descripcion1", result.get(0).getComentario().getHilo().getTopico().getDescripcion());
        assertNotNull(result.get(0).getComentario().getHilo().getTopico().getCategoria());
        assertEquals(1L, result.get(0).getComentario().getHilo().getTopico().getCategoria().getIdCategorias());
        assertEquals("Categoria1", result.get(0).getComentario().getHilo().getTopico().getCategoria().getNombre());
        assertEquals("Descripcion1", result.get(0).getComentario().getHilo().getTopico().getCategoria().getDescripcion());
        assertNotNull(result.get(0).getComentario().getHilo().getUsuario());
        assertEquals(1L, result.get(0).getComentario().getHilo().getUsuario().getIdUsuario());
        assertEquals("Usuario1", result.get(0).getComentario().getHilo().getUsuario().getUser());
        assertEquals("Password1", result.get(0).getComentario().getHilo().getUsuario().getPassword());
        assertEquals("Email@gmail.com", result.get(0).getComentario().getHilo().getUsuario().getEmail());
        assertNotNull(result.get(0).getComentario().getUsuario());
        assertEquals(1L, result.get(0).getComentario().getUsuario().getIdUsuario());
        assertEquals("Usuario1", result.get(0).getComentario().getUsuario().getUser());
        assertEquals("Password1", result.get(0).getComentario().getUsuario().getPassword());
        assertEquals("Email@gmail.com", result.get(0).getComentario().getUsuario().getEmail());
        assertEquals(2L, result.get(1).getIdArchivo());
        assertEquals("Archivo 2", result.get(1).getNombre());
        assertEquals("Tipo 2", result.get(1).getTipo());
        assertEquals("Link 2", result.get(1).getLink());
        assertNotNull(result.get(1).getComentario());
        assertEquals(1L, result.get(1).getComentario().getIdComentario());
        assertEquals("Mensaje 1", result.get(1).getComentario().getMensaje());
        assertEquals(1L, result.get(1).getComentario().getHilo().getIdHilo());
        assertEquals("Título 1", result.get(1).getComentario().getHilo().getTitulo());
        assertEquals("Mensaje 1", result.get(1).getComentario().getHilo().getMensaje());
        assertNotNull(result.get(1).getComentario().getHilo().getTopico());
        assertEquals(1L, result.get(1).getComentario().getHilo().getTopico().getIdTopico());
        assertEquals("Topico1", result.get(1).getComentario().getHilo().getTopico().getNombre());
        assertEquals("Descripcion1", result.get(1).getComentario().getHilo().getTopico().getDescripcion());
        assertNotNull(result.get(1).getComentario().getHilo().getTopico().getCategoria());
        assertEquals(1L, result.get(1).getComentario().getHilo().getTopico().getCategoria().getIdCategorias());
        assertEquals("Categoria1", result.get(1).getComentario().getHilo().getTopico().getCategoria().getNombre());
        assertEquals("Descripcion1", result.get(1).getComentario().getHilo().getTopico().getCategoria().getDescripcion());
        assertNotNull(result.get(1).getComentario().getHilo().getUsuario());
        assertEquals(1L, result.get(1).getComentario().getHilo().getUsuario().getIdUsuario());
        assertEquals("Usuario1", result.get(1).getComentario().getHilo().getUsuario().getUser());
        assertEquals("Password1", result.get(1).getComentario().getHilo().getUsuario().getPassword());
        assertEquals("Email@gmail.com", result.get(1).getComentario().getHilo().getUsuario().getEmail());
        assertNotNull(result.get(1).getComentario().getUsuario());
        assertEquals(1L, result.get(1).getComentario().getUsuario().getIdUsuario());
        assertEquals("Usuario1", result.get(1).getComentario().getUsuario().getUser());
        assertEquals("Password1", result.get(1).getComentario().getUsuario().getPassword());
        assertEquals("Email@gmail.com", result.get(1).getComentario().getUsuario().getEmail());
    }

    @Test
    public void testGuardarArchivo(){
        //Give
        Categoria categoriaMock = new Categoria(1L, "Categoria1", "Descripcion1");
        Topico topicoMock = new Topico(1L, "Topico1", "Descripcion1", categoriaMock);
        Usuario usuarioMock = new Usuario(1l, "Usuario1", "Password1", "Email@gmail.com");
        Hilo hiloMock = new Hilo(1L, "Título 1", "Mensaje 1", topicoMock, usuarioMock);
        Comentario comentarioMock = new Comentario(1L, "Mensaje 1", hiloMock, usuarioMock);
        Archivo archivoMock = new Archivo(1L, "Archivo 1", "Tipo 1", "Link 1", comentarioMock);
        when(archivoRepository.save(archivoMock)).thenReturn(archivoMock);
        //When
        Categoria categoria = new Categoria(1L, "Categoria1", "Descripcion1");
        Topico topico = new Topico(1L, "Topico1", "Descripcion1", categoria);
        Usuario usuario = new Usuario(1l, "Usuario1", "Password1", "Email@gmail.com");
        Hilo hilo = new Hilo(1L, "Título 1", "Mensaje 1", topico, usuario);
        Comentario comentario = new Comentario(1L, "Mensaje 1", hilo, usuario);
        Archivo archivoAGuardar = new Archivo(1L, "Archivo 1", "Tipo 1", "Link 1", comentario);
        Archivo archivoGuardado = archivoService.guardarArchivo(archivoAGuardar);
        //Then
        assertNotNull(archivoGuardado);
        assertEquals(1L, archivoGuardado.getIdArchivo());
        assertEquals("Archivo 1", archivoGuardado.getNombre());
        assertEquals("Tipo 1", archivoGuardado.getTipo());
        assertEquals("Link 1", archivoGuardado.getLink());
        assertNotNull(archivoGuardado.getComentario());
        assertEquals(1L, archivoGuardado.getComentario().getIdComentario());
        assertEquals("Mensaje 1", archivoGuardado.getComentario().getMensaje());
        assertEquals(1L, archivoGuardado.getComentario().getHilo().getIdHilo());
        assertEquals("Título 1", archivoGuardado.getComentario().getHilo().getTitulo());
        assertEquals("Mensaje 1", archivoGuardado.getComentario().getHilo().getMensaje());
        assertNotNull(archivoGuardado.getComentario().getHilo().getTopico());
        assertEquals(1L, archivoGuardado.getComentario().getHilo().getTopico().getIdTopico());
        assertEquals("Topico1", archivoGuardado.getComentario().getHilo().getTopico().getNombre());
        assertEquals("Descripcion1", archivoGuardado.getComentario().getHilo().getTopico().getDescripcion());
        assertNotNull(archivoGuardado.getComentario().getHilo().getTopico().getCategoria());
        assertEquals(1L, archivoGuardado.getComentario().getHilo().getTopico().getCategoria().getIdCategorias());
        assertEquals("Categoria1", archivoGuardado.getComentario().getHilo().getTopico().getCategoria().getNombre());
        assertEquals("Descripcion1", archivoGuardado.getComentario().getHilo().getTopico().getCategoria().getDescripcion());
        assertNotNull(archivoGuardado.getComentario().getHilo().getUsuario());
        assertEquals(1L, archivoGuardado.getComentario().getHilo().getUsuario().getIdUsuario());
        assertEquals("Usuario1", archivoGuardado.getComentario().getHilo().getUsuario().getUser());
        assertEquals("Password1", archivoGuardado.getComentario().getHilo().getUsuario().getPassword());
        assertEquals("Email@gmail.com", archivoGuardado.getComentario().getHilo().getUsuario().getEmail());
        assertNotNull(archivoGuardado.getComentario().getUsuario());
        assertEquals(1L, archivoGuardado.getComentario().getUsuario().getIdUsuario());
        assertEquals("Usuario1", archivoGuardado.getComentario().getUsuario().getUser());
        assertEquals("Password1", archivoGuardado.getComentario().getUsuario().getPassword());
        assertEquals("Email@gmail.com", archivoGuardado.getComentario().getUsuario().getEmail());
    }
}