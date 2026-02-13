package com.pollorosa.RickAndMorty.vista;

import com.pollorosa.RickAndMorty.model.Constantes;
import com.pollorosa.RickAndMorty.model.DatosPersonaje;
import com.pollorosa.RickAndMorty.model.DatosResultado;
import com.pollorosa.RickAndMorty.model.Personaje;
import com.pollorosa.RickAndMorty.service.ConsultaDatos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Principal {
    ConsultaDatos consultaDatos = new ConsultaDatos();

    public void iniciar() {
        System.out.println("Descargando la información...");
        List<DatosResultado> resultados = new ArrayList<>();
        String url = Constantes.URL_API;
        int contadorPaginas = 0;
        do {
            var datos = consultaDatos.cargarDatos(url, DatosResultado.class);
            resultados.add(datos);
            url = datos.info().next();
            contadorPaginas++;
        } while(url != null && contadorPaginas < 10);

        /*List<DatosPersonaje> datosPersonajes = resultados.stream()
                .flatMap(r -> r.personajes().stream())
                .collect(Collectors.toList());*/

        List<Personaje> personajes = resultados.stream()
                .flatMap(r -> r.personajes().stream())
                .map(Personaje::new)
                .collect(Collectors.toList());

        /*personajes.stream()
                .filter(p -> p.getNombre().contains("Rick"))
                        .forEach(System.out::println);*/

        System.out.println("Los 10 personajes Ricks más antiguos de la serie:");
        personajes.stream()
                .filter(p -> p.getNombre().contains("Rick") && p.getFechaDeCreacion() != null)
                //.peek(p -> System.out.println("Filtro humano y fecha not null" + p))
                .sorted(Comparator.comparing(Personaje::getFechaDeCreacion))
                .limit(10)
                .forEach(System.out::println);

        System.out.println("Top 10 personajes con más apariciones en episodios:");
        personajes.stream()
                .sorted(Comparator.comparing(Personaje::getEpisodios).reversed())
                .limit(10)
                .map(p -> p.getNombre() + " con " + p.getEpisodios() + " episodios.")
                .forEach(System.out::println);
    }
}
