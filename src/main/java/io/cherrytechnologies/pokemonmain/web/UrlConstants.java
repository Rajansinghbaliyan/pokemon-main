package io.cherrytechnologies.pokemonmain.web;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class UrlConstants {
    @Value("${ability.url}")
    private String ABILITY_URL;
    @Value("${ability.url.save.all}")
    private String ABILITY_URL_SAVE_ALL;
}
