package io.cherrytechnologies.pokemonmain.web.responsedto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@ToString
public class AbilityDto implements Serializable {
    private String name;
    private String url;
    private boolean is_hidden;
    private int slot;
    private UUID pokemonId;
}
