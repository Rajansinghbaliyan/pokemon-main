package io.cherrytechnologies.pokemonmain.controllers;


import io.cherrytechnologies.pokemonmain.dto.PokemonDto;
import io.cherrytechnologies.pokemonmain.dto.ResponseDto;
import io.cherrytechnologies.pokemonmain.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/pokemon")
public class PokemonController {

    private final PokemonService service;

    @Autowired
    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<PokemonDto>> getById(@PathVariable int id) {
        return ResponseEntity.ok(
                new ResponseDto<>(service.getById(id))
        );
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDto<PokemonDto>> savePokemon(@RequestBody PokemonDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new ResponseDto<>(service.save(dto)));
    }

    @PostMapping("/all")
    public ResponseEntity<ResponseDto<List<PokemonDto>>> saveAllPokemon(@RequestBody List<PokemonDto> dtos) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new ResponseDto<>(service.saveAll(dtos)));
    }

    @GetMapping("/")
    public ResponseEntity<ResponseDto<List<PokemonDto>>> getAll() {
        return ResponseEntity
                .ok(new ResponseDto<>(service.getAll()));
    }
}
