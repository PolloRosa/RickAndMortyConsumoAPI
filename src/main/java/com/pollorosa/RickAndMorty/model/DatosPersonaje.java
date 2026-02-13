package com.pollorosa.RickAndMorty.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosPersonaje(
        @JsonAlias("id") Integer id,
        @JsonAlias("name") String nombre,
        @JsonAlias("status") String estado,
        @JsonAlias("species") String especie,
        @JsonAlias("type") String tipo,
        @JsonAlias("gender") String genero,
        @JsonAlias("origin") DatosOrigen origen,
        @JsonAlias("location") DatosUbicacion ubicacion,
        @JsonAlias("episode") List<String> episodios,
        @JsonAlias("created") String fechaCreacion) {
}
