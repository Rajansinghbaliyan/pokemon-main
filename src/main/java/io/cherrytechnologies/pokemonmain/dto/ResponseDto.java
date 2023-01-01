package io.cherrytechnologies.pokemonmain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class ResponseDto<T> implements Serializable {
    private final Date time = new Date();
    private T data;

    public ResponseDto(T data) {
        this.data = data;
    }
}
