package com.pollorosa.RickAndMorty.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosOrigen(
        @JsonAlias("name") String nombre,
        @JsonAlias("url") String url) {
}
