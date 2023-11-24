package com.learnsyc.appweb.serializers.flashcard;

import com.learnsyc.appweb.serializers.usuario.UserSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlashCardSerializer {
    Long id;
    String nombre;
    String descripcion;
    int numCards;
    byte[] archivo;
    UserSerializer usuario;
}
