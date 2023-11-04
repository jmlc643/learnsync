package com.learnsyc.appweb.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository; 
import com.learnsyc.appweb.models.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long>{
}
