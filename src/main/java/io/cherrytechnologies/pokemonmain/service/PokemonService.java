package io.cherrytechnologies.pokemonmain.service;

import io.cherrytechnologies.pokemonmain.domain.Pokemon;
import io.cherrytechnologies.pokemonmain.dto.PokemonDto;
import io.cherrytechnologies.pokemonmain.exceptions.PokemonNotAvailable;
import io.cherrytechnologies.pokemonmain.repository.PokemonRepository;
import io.cherrytechnologies.pokemonmain.web.UrlConstants;
import io.cherrytechnologies.pokemonmain.web.WebCall;
import io.cherrytechnologies.pokemonmain.web.responsedto.AbilityDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PokemonService {
    private final PokemonRepository repository;

    private final WebCall webCall;

    private final UrlConstants constants;

    @Autowired
    public PokemonService(PokemonRepository repository, WebCall webCall, UrlConstants constants) {
        this.repository = repository;
        this.webCall = webCall;
        this.constants = constants;
    }

    public PokemonDto getById(int id) {
        log.debug(String.format("Get pokemon by id: %d", id));
        Pokemon pokemon = repository.getPokemonsById(id)
                .orElseThrow(() -> new PokemonNotAvailable("Pokemon is not available id: " + id));

        @SuppressWarnings("unchecked")
        Class<List<AbilityDto>> listClass = (Class) List.class;

        Optional<List<AbilityDto>> abilityDtos = webCall
                .getById(listClass, pokemon.getUuid(), constants.getABILITY_BY_POKEMON_ID())
                .blockOptional();

        PokemonDto pokemonDto = pokemon.pokemonToDto();

        abilityDtos.ifPresent(
                pokemonDto::setAbilities
        );

        return pokemonDto;
    }

    public List<PokemonDto> getAll() {
        log.debug("Get all pokemons");
        return repository
                .findAll()
                .stream()
                .map(Pokemon::pokemonToDto)
                .toList();
    }

    public List<PokemonDto> saveAll(List<PokemonDto> dtos) {
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

    public PokemonDto save(PokemonDto dto) {
        log.debug(String.format("Save pokemon by id: %d", dto.getId()));
        Pokemon save = repository.save(dto.dtoToPokemon());

        dto
                .getAbilities()
                .stream()
                .forEach(abilityDto -> abilityDto.setPokemonId(save.getUuid()));

        @SuppressWarnings("unchecked")
        Class<List<AbilityDto>> listClass = (Class) List.class;

        Optional<List<AbilityDto>> abilityDtos = webCall
                .saveEntity(dto.getAbilities(), constants.getABILITY_URL_SAVE_ALL(), listClass)
                .blockOptional();

        PokemonDto pokemonDto = save.pokemonToDto();

        abilityDtos
                .ifPresent(pokemonDto::setAbilities);

        return pokemonDto;
    }
}
