package com.learnsyc.appweb.serializers.flashcard;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteFlashCardRequest {
    @NotNull(message = "Dato vacio")
    Long id;
}
