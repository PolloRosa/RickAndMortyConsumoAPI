package com.pollorosa.RickAndMorty.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosInfo(
        @JsonAlias("count") Integer total,
        @JsonAlias("pages") Integer totalDePaginas,
        @JsonAlias("next") String next,
        @JsonAlias("prev") String prev) {
}
