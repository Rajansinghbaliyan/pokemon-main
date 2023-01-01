package io.cherrytechnologies.pokemonmain.service;

import io.cherrytechnologies.pokemonmain.domain.Pokemon;
import io.cherrytechnologies.pokemonmain.dto.PokemonDto;
import io.cherrytechnologies.pokemonmain.exceptions.PokemonNotAvailable;
import io.cherrytechnologies.pokemonmain.repository.PokemonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PokemonService {
    private final PokemonRepository repository;

    @Autowired
    public PokemonService(PokemonRepository repository) {
        this.repository = repository;
    }

    public PokemonDto getById(int id){
        log.debug(String.format("Get pokemon by id: %d",id));
        return repository.getPokemonsById(id)
                .orElseThrow(() -> new PokemonNotAvailable("Pokemon is not available id: "+ id))
                .pokemonToDto();
    }

    public List<PokemonDto> getAll(){
        log.debug("Get all pokemons");
        return repository
                .findAll()
                .stream()
                .map(Pokemon::pokemonToDto)
                .toList();
    }

    public List<PokemonDto> saveAll(List<PokemonDto> dtos){
        log.debug("Saving List of pokemons with saveAll()");
        List<Pokemon> pokemons = dtos
                .stream()
                .map(PokemonDto::dtoToPokemon)
                .toList();

        return repository
                .saveAll(pokemons)
                .stream()
                .map(Pokemon::pokemonToDto)
                .toList();

    }

    public PokemonDto save(PokemonDto dto){
        log.debug(String.format("Save pokemon by id: %d",dto.getId()));
        return repository.save(dto.dtoToPokemon()).pokemonToDto();
    }
}
