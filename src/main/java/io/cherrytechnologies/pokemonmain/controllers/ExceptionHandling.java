package io.cherrytechnologies.pokemonmain.controllers;

import io.cherrytechnologies.pokemonmain.dto.ResponseDto;
import io.cherrytechnologies.pokemonmain.exceptions.PokemonNotAvailable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandling {

    @ExceptionHandler(PokemonNotAvailable.class)
    public ResponseEntity<ResponseDto<Object>> handlePokemonNotFound(PokemonNotAvailable exception) {
        log.debug(exception.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ResponseDto
                        .builder()
                        .data(exception.getMessage())
                        .build()
                );
    }
}
