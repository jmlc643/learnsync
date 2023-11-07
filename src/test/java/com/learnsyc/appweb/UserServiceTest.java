package com.learnsyc.appweb;

import com.learnsyc.appweb.repositories.UserRepository;
import com.learnsyc.appweb.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    public UserService userService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarUsuarios(){
        //Arranque -> GIVEN
        //Accion -> WHEN
        //Assert -> THEN
    }
}
