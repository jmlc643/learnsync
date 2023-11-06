package com.learnsyc.appweb.repositories;

import com.learnsyc.appweb.models.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivoRepository extends JpaRepository<Archivo, Long> {
}
