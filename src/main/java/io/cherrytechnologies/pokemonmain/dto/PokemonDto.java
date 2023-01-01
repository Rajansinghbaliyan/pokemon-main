package io.cherrytechnologies.pokemonmain.dto;

import io.cherrytechnologies.pokemonmain.domain.Pokemon;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class PokemonDto implements Comparable<PokemonDto>, Serializable {
    private int id;
    private String name;
    private int base_experience;
    private int height;
    private boolean is_default;
    private String location_area_encounters;
    private int weight;

    @Override
    public int compareTo(PokemonDto o) {
        return Integer.compare(this.id, o.id);
    }

    public Pokemon dtoToPokemon(){
        Pokemon pokemon = new Pokemon();
        pokemon.setId(this.getId());
        pokemon.setName(this.getName());
        pokemon.setBase_experience(this.getBase_experience());
        pokemon.setWeight(this.getWeight());
        pokemon.setHeight(this.getHeight());
        pokemon.setLocation_area_encounters(this.getLocation_area_encounters());
        pokemon.is_default = this.is_default();
        return pokemon;
    }
}
