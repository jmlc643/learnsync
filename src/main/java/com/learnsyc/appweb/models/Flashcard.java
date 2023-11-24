package com.learnsyc.appweb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "flashcards")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Flashcard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_flashcard")
    Long idFlashcard;

    @Column(name = "nombre", nullable = false)
    String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    String descripcion;

    @Column(name = "num_cards")
    String numCards;

    @Column(name = "id_propietario")
    String idPropietario;

    @Lob
    @Column(name = "archivo")
    byte[] archivo;
}