package com.learnsyc.appweb.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository; 
import com.learnsyc.appweb.models.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUser(String user);
    Usuario findByUserAndPassword(String user, String password);
}
