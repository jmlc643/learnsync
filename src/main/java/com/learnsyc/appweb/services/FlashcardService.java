package com.learnsyc.appweb.services;

import com.learnsyc.appweb.models.Flashcard;

import com.learnsyc.appweb.repositories.FlashCardRepository;
import com.learnsyc.appweb.serializers.flashcard.DeleteFlashCardRequest;
import com.learnsyc.appweb.serializers.flashcard.EditFlashCardRequest;
import com.learnsyc.appweb.serializers.flashcard.FlashCardSerializer;
import com.learnsyc.appweb.serializers.usuario.UserSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashcardService {
    @Autowired
    FlashCardRepository flashcardRepository;

    // Método para listar todas las flashcards
    public List<Flashcard> listarFlashcards() {
        return flashcardRepository.findAll();
    }

    // Método para guardar una nueva flashcard
    public Flashcard guardarFlashcard(Flashcard flashcard) {
        return flashcardRepository.save(flashcard);
    }

    public Flashcard guardarCambios(Flashcard flashcard){return flashcardRepository.saveAndFlush(flashcard);}

    // Método para editar una flashcard existente
    public FlashCardSerializer editarFlashcard(EditFlashCardRequest request) {
        Flashcard flashcard = encontrarFlashCard(request.getId());
        flashcard.setNombre(request.getNombre());
        flashcard.setDescripcion(request.getDescripcion());
        flashcard.setNumCards(flashcard.getNumCards());
        guardarCambios(flashcard);
        return retornarFlashcard(flashcard);
    }

    public Flashcard encontrarFlashCard(Long id){
        return flashcardRepository.findByIdFlashcard(id);
    }

    // Método para eliminar una flashcard
    public Flashcard eliminarFlashcard(DeleteFlashCardRequest request) {
        Flashcard flashcard = encontrarFlashCard(request.getId());
        flashcardRepository.deleteById(request.getId());
        return flashcard;
    }

    // Método para retornar una flashcard específica
    public FlashCardSerializer retornarFlashcard(Flashcard flashcard) {
        return new FlashCardSerializer(flashcard.getIdFlashcard(), flashcard.getNombre(), flashcard.getDescripcion(), flashcard.getNumCards(), flashcard.getArchivo(),
                new UserSerializer(flashcard.getUsuario().getUser(), flashcard.getUsuario().getEmail(), flashcard.getUsuario().getNroPuntos()));
    }
}