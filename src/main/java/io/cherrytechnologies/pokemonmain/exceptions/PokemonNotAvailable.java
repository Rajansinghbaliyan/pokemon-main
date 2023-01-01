package io.cherrytechnologies.pokemonmain.exceptions;

public class PokemonNotAvailable extends RuntimeException{
    public PokemonNotAvailable() {
        super();
    }

    public PokemonNotAvailable(String message) {
        super(message);
    }

    public PokemonNotAvailable(String message, Throwable cause) {
        super(message, cause);
    }

    public PokemonNotAvailable(Throwable cause) {
        super(cause);
    }
}
