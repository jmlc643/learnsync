package com.learnsyc.appweb.repositories;

import com.learnsyc.appweb.models.Categoria;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository; 
import com.learnsyc.appweb.models.Topico;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long>{
    Topico findByNombre(String nombre);
    Topico saveAndFlush(Topico topico);

    void deleteById(Long Id);

    boolean existsTopicoByNombre(String nombre);

    List<Topico> findAllByCategoria(Categoria categoria);
}
