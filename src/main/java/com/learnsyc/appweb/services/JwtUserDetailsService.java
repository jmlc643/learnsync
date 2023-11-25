package com.learnsyc.appweb.services;

import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class JwtUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);
    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("---loadUserByUsername called.---");
        Optional<Usuario> user = Optional.ofNullable(userRepository.findByUser(username));
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException("User "+username+" not found.");
        }
    }


}
