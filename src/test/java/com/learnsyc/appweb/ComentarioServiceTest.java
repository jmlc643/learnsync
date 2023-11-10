package com.learnsyc.appweb;

import com.learnsyc.appweb.excepciones.ResourceNotExistsException;
import com.learnsyc.appweb.models.*;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

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
        Categoria categoriaMock = new Categoria(1L, "Categoria1", "Descripcion1");
        Topico topicoMock = new Topico(1L, "Topico1", "Descripcion1", categoriaMock);
        Usuario usuarioMock = new Usuario(1l, "Usuario1", "Password1", "Email@gmail.com");
        Hilo hilo = new Hilo(1L, "Título 1", "Mensaje 1", topicoMock, usuarioMock);
        Comentario comentario1 = new Comentario(1L, "Mensaje 1", hilo, usuarioMock);
        Comentario comentario2 = new Comentario(2L, "Mensaje 2", hilo, usuarioMock);
        List<Comentario> comentarios = Arrays.asList(comentario1, comentario2);

        when(comentarioRepository.findAll()).thenReturn(comentarios);

        // When
        List<Comentario> result = comentarioService.listarComentario();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getIdComentario());
        assertEquals("Mensaje 1", result.get(0).getMensaje());
        assertEquals(1L, result.get(0).getHilo().getIdHilo());
        assertEquals("Título 1", result.get(0).getHilo().getTitulo());
        assertEquals("Mensaje 1", result.get(0).getHilo().getMensaje());
        assertNotNull(result.get(0).getHilo().getTopico());
        assertEquals(1L, result.get(0).getHilo().getTopico().getIdTopico());
        assertEquals("Topico1", result.get(0).getHilo().getTopico().getNombre());
        assertEquals("Descripcion1", result.get(0).getHilo().getTopico().getDescripcion());
        assertNotNull(result.get(0).getHilo().getTopico().getCategoria());
        assertEquals(1L, result.get(0).getHilo().getTopico().getCategoria().getIdCategorias());
        assertEquals("Categoria1", result.get(0).getHilo().getTopico().getCategoria().getNombre());
        assertEquals("Descripcion1", result.get(0).getHilo().getTopico().getCategoria().getDescripcion());
        assertNotNull(result.get(0).getHilo().getUsuario());
        assertEquals(1L, result.get(0).getHilo().getUsuario().getIdUsuario());
        assertEquals("Usuario1", result.get(0).getHilo().getUsuario().getUser());
        assertEquals("Password1", result.get(0).getHilo().getUsuario().getPassword());
        assertEquals("Email@gmail.com", result.get(0).getHilo().getUsuario().getEmail());
        assertNotNull(result.get(0).getUsuario());
        assertEquals(1L, result.get(0).getUsuario().getIdUsuario());
        assertEquals("Usuario1", result.get(0).getUsuario().getUser());
        assertEquals("Password1", result.get(0).getUsuario().getPassword());
        assertEquals("Email@gmail.com", result.get(0).getUsuario().getEmail());
        assertEquals(2L, result.get(1).getIdComentario());
        assertEquals("Mensaje 2", result.get(1).getMensaje());
        assertEquals(1L, result.get(1).getHilo().getIdHilo());
        assertEquals("Título 1", result.get(1).getHilo().getTitulo());
        assertEquals("Mensaje 1", result.get(1).getHilo().getMensaje());
        assertNotNull(result.get(1).getHilo().getTopico());
        assertEquals(1L, result.get(1).getHilo().getTopico().getIdTopico());
        assertEquals("Topico1", result.get(1).getHilo().getTopico().getNombre());
        assertEquals("Descripcion1", result.get(1).getHilo().getTopico().getDescripcion());
        assertNotNull(result.get(1).getHilo().getTopico().getCategoria());
        assertEquals(1L, result.get(1).getHilo().getTopico().getCategoria().getIdCategorias());
        assertEquals("Categoria1", result.get(1).getHilo().getTopico().getCategoria().getNombre());
        assertEquals("Descripcion1", result.get(1).getHilo().getTopico().getCategoria().getDescripcion());
        assertNotNull(result.get(1).getHilo().getUsuario());
        assertEquals(1L, result.get(1).getHilo().getUsuario().getIdUsuario());
        assertEquals("Usuario1", result.get(1).getHilo().getUsuario().getUser());
        assertEquals("Password1", result.get(1).getHilo().getUsuario().getPassword());
        assertEquals("Email@gmail.com", result.get(1).getHilo().getUsuario().getEmail());
        assertEquals(1L, result.get(1).getUsuario().getIdUsuario());
        assertEquals("Usuario1", result.get(1).getUsuario().getUser());
        assertEquals("Password1", result.get(1).getUsuario().getPassword());
        assertEquals("Email@gmail.com", result.get(1).getUsuario().getEmail());
    }

    @Test
    public void testGuardarComentario(){
        //Give
        Categoria categoriaMock = new Categoria(1L, "Categoria1", "Descripcion1");
        Topico topicoMock = new Topico(1L, "Topico1", "Descripcion1", categoriaMock);
        Usuario usuarioMock = new Usuario(1l, "Usuario1", "Password1", "Email@gmail.com");
        Hilo hiloMock = new Hilo(1L, "Título 1", "Mensaje 1", topicoMock, usuarioMock);
        Comentario comentarioMock = new Comentario(1L, "Mensaje 1", hiloMock, usuarioMock);
        when(comentarioRepository.save(comentarioMock)).thenReturn(comentarioMock);
        //When
        Categoria categoria = new Categoria(1L, "Categoria1", "Descripcion1");
        Topico topico = new Topico(1L, "Topico1", "Descripcion1", categoria);
        Usuario usuario = new Usuario(1l, "Usuario1", "Password1", "Email@gmail.com");
        Hilo hilo = new Hilo(1L, "Título 1", "Mensaje 1", topico, usuario);
        Comentario comentarioAGuardar = new Comentario(1L, "Mensaje 1", hilo, usuario);
        Comentario comentarioGuardado = comentarioService.guardarComentario(comentarioAGuardar);
        //Then
        assertNotNull(comentarioGuardado);
        assertEquals(1L, comentarioGuardado.getIdComentario());
        assertEquals("Mensaje 1", comentarioGuardado.getMensaje());
        assertEquals(1L, comentarioGuardado.getHilo().getIdHilo());
        assertEquals("Título 1", comentarioGuardado.getHilo().getTitulo());
        assertEquals("Mensaje 1", comentarioGuardado.getHilo().getMensaje());
        assertNotNull(comentarioGuardado.getHilo().getTopico());
        assertEquals(1L, comentarioGuardado.getHilo().getTopico().getIdTopico());
        assertEquals("Topico1", comentarioGuardado.getHilo().getTopico().getNombre());
        assertEquals("Descripcion1", comentarioGuardado.getHilo().getTopico().getDescripcion());
        assertNotNull(comentarioGuardado.getHilo().getTopico().getCategoria());
        assertEquals(1L, comentarioGuardado.getHilo().getTopico().getCategoria().getIdCategorias());
        assertEquals("Categoria1", comentarioGuardado.getHilo().getTopico().getCategoria().getNombre());
        assertEquals("Descripcion1", comentarioGuardado.getHilo().getTopico().getCategoria().getDescripcion());
        assertNotNull(comentarioGuardado.getHilo().getUsuario());
        assertEquals(1L, comentarioGuardado.getHilo().getUsuario().getIdUsuario());
        assertEquals("Usuario1", comentarioGuardado.getHilo().getUsuario().getUser());
        assertEquals("Password1", comentarioGuardado.getHilo().getUsuario().getPassword());
        assertEquals("Email@gmail.com", comentarioGuardado.getHilo().getUsuario().getEmail());
        assertNotNull(comentarioGuardado.getUsuario());
        assertEquals(1L, comentarioGuardado.getUsuario().getIdUsuario());
        assertEquals("Usuario1", comentarioGuardado.getUsuario().getUser());
        assertEquals("Password1", comentarioGuardado.getUsuario().getPassword());
        assertEquals("Email@gmail.com", comentarioGuardado.getUsuario().getEmail());
    }

    @Test
    public void testEncontrarComentario(){
        //Give
        Categoria categoriaMock = new Categoria(1L, "Categoria1", "Descripcion1");
        Topico topicoMock = new Topico(1L, "Topico1", "Descripcion1", categoriaMock);
        Usuario usuarioMock = new Usuario(1l, "Usuario1", "Password1", "Email@gmail.com");
        Hilo hiloMock = new Hilo(1L, "Título 1", "Mensaje 1", topicoMock, usuarioMock);
        Comentario comentarioMock = new Comentario(1L, "Mensaje 1", hiloMock, usuarioMock);
        when(comentarioRepository.existsById(1L)).thenReturn(true);
        when(comentarioRepository.findByIdComentario(1L)).thenReturn(comentarioMock);
        //When
        Comentario comentarioEncontrado = comentarioService.encontrarComentario(1L);
        //Then
        assertNotNull(comentarioEncontrado);
        assertEquals(1L, comentarioEncontrado.getIdComentario());
        assertEquals("Mensaje 1", comentarioEncontrado.getMensaje());
        assertEquals(1L, comentarioEncontrado.getHilo().getIdHilo());
        assertEquals("Título 1", comentarioEncontrado.getHilo().getTitulo());
        assertEquals("Mensaje 1", comentarioEncontrado.getHilo().getMensaje());
        assertNotNull(comentarioEncontrado.getHilo().getTopico());
        assertEquals(1L, comentarioEncontrado.getHilo().getTopico().getIdTopico());
        assertEquals("Topico1", comentarioEncontrado.getHilo().getTopico().getNombre());
        assertEquals("Descripcion1", comentarioEncontrado.getHilo().getTopico().getDescripcion());
        assertNotNull(comentarioEncontrado.getHilo().getTopico().getCategoria());
        assertEquals(1L, comentarioEncontrado.getHilo().getTopico().getCategoria().getIdCategorias());
        assertEquals("Categoria1", comentarioEncontrado.getHilo().getTopico().getCategoria().getNombre());
        assertEquals("Descripcion1", comentarioEncontrado.getHilo().getTopico().getCategoria().getDescripcion());
        assertNotNull(comentarioEncontrado.getHilo().getUsuario());
        assertEquals(1L, comentarioEncontrado.getHilo().getUsuario().getIdUsuario());
        assertEquals("Usuario1", comentarioEncontrado.getHilo().getUsuario().getUser());
        assertEquals("Password1", comentarioEncontrado.getHilo().getUsuario().getPassword());
        assertEquals("Email@gmail.com", comentarioEncontrado.getHilo().getUsuario().getEmail());
        assertNotNull(comentarioEncontrado.getUsuario());
        assertEquals(1L, comentarioEncontrado.getUsuario().getIdUsuario());
        assertEquals("Usuario1", comentarioEncontrado.getUsuario().getUser());
        assertEquals("Password1", comentarioEncontrado.getUsuario().getPassword());
        assertEquals("Email@gmail.com", comentarioEncontrado.getUsuario().getEmail());
    }

    @Test
    public void testEncontrarComentario_ComentarioNoEncontrado(){
        Comentario comentario;
        try{
            comentario = comentarioService.encontrarComentario(1L);
        }catch (ResourceNotExistsException e){
            assertEquals("El comentario #1 no existe", e.getMessage());
        }
    }

    @Test
    public void testEliminarComentario(){
        Categoria categoriaMock = new Categoria(1L, "Categoria1", "Descripcion1");
        Topico topicoMock = new Topico(1L, "Topico1", "Descripcion1", categoriaMock);
        Usuario usuarioMock = new Usuario(1l, "Usuario1", "Password1", "Email@gmail.com");
        Hilo hiloMock = new Hilo(1L, "Título 1", "Mensaje 1", topicoMock, usuarioMock);
        Comentario comentarioMock = new Comentario(1L, "Mensaje 1", hiloMock, usuarioMock);
        comentarioRepository.deleteById(1L);
        verify(comentarioRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testEditarComentario(){
        Categoria categoriaMock = new Categoria(1L, "Categoria1", "Descripcion1");
        Topico topicoMock = new Topico(1L, "Topico1", "Descripcion1", categoriaMock);
        Usuario usuarioMock = new Usuario(1l, "Usuario1", "Password1", "Email@gmail.com");
        Hilo hiloMock = new Hilo(1L, "Título 1", "Mensaje 1", topicoMock, usuarioMock);
        Comentario comentarioMock = new Comentario(1L, "Mensaje 1", hiloMock, usuarioMock);
        comentarioMock.setMensaje("Mensaje  1");
        comentarioMock.setEsEditado(true);
        comentarioService.guardarCambios(comentarioMock);
        verify(comentarioRepository, times(1)).saveAndFlush(comentarioMock);
    }
}