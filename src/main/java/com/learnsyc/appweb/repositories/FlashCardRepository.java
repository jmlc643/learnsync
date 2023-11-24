package com.learnsyc.appweb.repositories;

import com.learnsyc.appweb.models.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashCardRepository extends JpaRepository<Flashcard, Long> {
    Flashcard saveAndFlush(Flashcard flashcard);
    Flashcard findByIdFlashcard(Long id);
}
