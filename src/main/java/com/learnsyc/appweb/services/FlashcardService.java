package com.learnsyc.appweb.services;

import com.learnsyc.appweb.models.Flashcard;
import com.learnsyc.appweb.repositories.FlashcardRepository;  // Asegúrate de tener el repositorio correspondiente
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashcardService {
    @Autowired
    FlashcardRepository flashcardRepository;

    // Método para listar todas las flashcards
    public List<Flashcard> listarFlashcards() {
        return flashcardRepository.findAll();
    }

    // Método para guardar una nueva flashcard
    public Flashcard guardarFlashcard(Flashcard flashcard) {
        return flashcardRepository.save(flashcard);
    }

    // Método para editar una flashcard existente
    public Flashcard editarFlashcard(Flashcard flashcard) {
        return flashcardRepository.save(flashcard);
    }

    // Método para eliminar una flashcard
    public void eliminarFlashcard(Long idFlashcard) {
        flashcardRepository.deleteById(idFlashcard);
    }

    // Método para retornar una flashcard específica
    public Flashcard retornarFlashcard(Long idFlashcard) {
        return flashcardRepository.findById(idFlashcard).orElse(null);
    }
}