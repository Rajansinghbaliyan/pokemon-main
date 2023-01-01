package io.cherrytechnologies.pokemonmain.repository;

import io.cherrytechnologies.pokemonmain.domain.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, UUID> {
    Optional<Pokemon> getPokemonsById(int id);
}
