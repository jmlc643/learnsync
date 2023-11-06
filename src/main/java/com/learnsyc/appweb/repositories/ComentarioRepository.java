package com.learnsyc.appweb.repositories;

import com.learnsyc.appweb.models.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    Comentario findByIdComentario(Long id);
}
