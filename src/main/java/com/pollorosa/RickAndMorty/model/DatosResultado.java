package com.pollorosa.RickAndMorty.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosResultado(
        @JsonAlias("info") DatosInfo info,
        @JsonAlias("results") List<DatosPersonaje> personajes) {
}
