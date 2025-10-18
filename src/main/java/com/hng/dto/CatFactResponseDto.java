package com.hng.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CatFactResponseDto {

    @JsonProperty("fact")
    private String fact;

    @JsonProperty("length")
    private Integer length;


}
