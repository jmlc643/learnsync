package com.learnsyc.appweb.repositories;

import com.learnsyc.appweb.models.Hilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HiloRepository extends JpaRepository<Hilo, Long>{
}
