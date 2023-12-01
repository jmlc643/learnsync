package com.learnsyc.appweb.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository; 
import com.learnsyc.appweb.models.Usuario;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUser(String user);
    Usuario saveAndFlush(Usuario usuario);
    boolean existsUsuarioByUser(String user);
    boolean existsUsuarioByEmail(String email);

    Usuario findByEmail(String email);
}
