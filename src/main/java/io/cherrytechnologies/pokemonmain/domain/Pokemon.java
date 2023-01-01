package io.cherrytechnologies.pokemonmain.domain;

import io.cherrytechnologies.pokemonmain.dto.PokemonDto;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Pokemon extends Base {
    private int id;
    private String name;
    private int base_experience;
    private int height;
    public boolean is_default;
    private String location_area_encounters;
    private int weight;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pokemon pokemon = (Pokemon) o;
        return id == pokemon.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    public PokemonDto pokemonToDto(){
        return PokemonDto
                .builder()
                .id(id)
                .name(name)
                .base_experience(base_experience)
                .height(height)
                .weight(weight)
                .location_area_encounters(location_area_encounters)
                .weight(weight)
                .build();
    }


}
