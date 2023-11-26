package com.learnsyc.appweb.repositories;

import com.learnsyc.appweb.models.ConfirmationToken;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository; 
import com.learnsyc.appweb.models.Usuario;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUser(String user);
    Optional<Usuario> findByUserAndPassword(String user, String password);
    Usuario saveAndFlush(Usuario usuario);
    boolean existsUsuarioByUser(String user);
    boolean existsUsuarioByEmail(String email);

}
