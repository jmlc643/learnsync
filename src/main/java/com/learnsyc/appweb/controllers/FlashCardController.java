package com.learnsyc.appweb.controllers;

import java.util.List;

import com.learnsyc.appweb.models.Flashcard;
import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.serializers.flashcard.DeleteFlashCardRequest;
import com.learnsyc.appweb.serializers.flashcard.EditFlashCardRequest;
import com.learnsyc.appweb.serializers.flashcard.FlashCardSerializer;
import com.learnsyc.appweb.serializers.flashcard.SaveFlashCardRequest;
import com.learnsyc.appweb.services.FlashcardService;
import com.learnsyc.appweb.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect -> Ignorar
@RestController
@RequestMapping("flashcard")
@CrossOrigin(origins = {"http://localhost:4200", "https://boisterous-sopapillas-1c3767.netlify.app/"})
public class FlashCardController {

    @Autowired FlashcardService flashcardService;
    @Autowired UserService userService;

    @GetMapping("/")
    public List<FlashCardSerializer> listarFlashCards(){
        return flashcardService.listarFlashcards().stream().map((it) -> flashcardService.retornarFlashcard(it)).toList();
    }

    @PostMapping("/")
    public Flashcard subirFlashCard(@Valid @RequestBody SaveFlashCardRequest request){
        Usuario usuario = userService.encontrarUsuarioPorUser(request.getUsername());
        Flashcard flashcard = new Flashcard(null, request.getNombre(), request.getDescripcion(), request.getNumCards(), usuario, request.getArchivo());
        return flashcardService.guardarFlashcard(flashcard);
    }

    @PutMapping("/")
    public FlashCardSerializer editarFlashCard(@Valid @RequestBody EditFlashCardRequest request){
        return flashcardService.editarFlashcard(request);
    }

    @DeleteMapping("/")
    public Flashcard eliminarFlashCard(@Valid @RequestBody DeleteFlashCardRequest request){
        return flashcardService.eliminarFlashcard(request);
    }
}
