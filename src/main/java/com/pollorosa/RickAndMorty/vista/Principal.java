package com.pollorosa.RickAndMorty.vista;

import com.pollorosa.RickAndMorty.model.Constantes;
import com.pollorosa.RickAndMorty.model.DatosPersonaje;
import com.pollorosa.RickAndMorty.model.DatosResultado;
import com.pollorosa.RickAndMorty.model.Personaje;
import com.pollorosa.RickAndMorty.service.ConsultaDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    ConsultaDatos consultaDatos = new ConsultaDatos();
    Scanner teclado = new Scanner(System.in);

    public void iniciar() {
        System.out.print("Descargando la información...");
        List<DatosResultado> resultados = new ArrayList<>();
        String url = Constantes.URL_API;
        int contadorPaginas = 0;
        do {
            var datos = consultaDatos.cargarDatos(url, DatosResultado.class);
            resultados.add(datos);
            url = datos.info().next();
            contadorPaginas++;
            System.out.print(".");
        } while(url != null && contadorPaginas < 10);
        System.out.println();

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

        System.out.println("\nLos 10 personajes Ricks más antiguos de la serie:");
        personajes.stream()
                .filter(p -> p.getNombre().contains("Rick") && p.getFechaDeCreacion() != null)
                //.peek(p -> System.out.println("Filtro humano y fecha not null" + p))
                .sorted(Comparator.comparing(Personaje::getFechaDeCreacion))
                .limit(10)
                .forEach(System.out::println);

        System.out.println("\nTop 10 personajes con más apariciones en episodios:");
        personajes.stream()
                .sorted(Comparator.comparing(Personaje::getEpisodios).reversed())
                .limit(10)
                .map(p -> p.getNombre() + " con " + p.getEpisodios() + " episodios.")
                .forEach(System.out::println);

        // Búsqueda de personajes por nombre
        System.out.println("\nBúsqueda de personaje".toUpperCase());
        System.out.println("Ingrese el nombre");
        var nombreBusqueda = teclado.nextLine();
        System.out.print("\nBuscando...");
        resultados = new ArrayList<>();
        url = Constantes.URL_API + "?name=" + nombreBusqueda;
        do {
            var datos = consultaDatos.cargarDatos(url, DatosResultado.class);
            resultados.add(datos);
            url = datos.info().next();
            System.out.print(".");
        } while(url != null);
        System.out.println();

        Optional<DatosPersonaje> personajeBuscado = resultados.stream()
                .flatMap(r -> r.personajes().stream())
                .filter(p -> p.nombre().toUpperCase().contains(nombreBusqueda.toUpperCase()))
                .findFirst();
        if(personajeBuscado.isPresent()) {
            System.out.println("Personaje encontrado:");
            System.out.println(personajeBuscado.get());
        } else {
            System.out.println("Personaje no encontrado.");
        }

        //System.out.println("\nTodos los personajes:");
        //personajes.forEach(System.out::println);

        // Trabajando con estadísticas
        System.out.println("\nEstadísticas en base a la cantidad de episodios".toUpperCase());
        IntSummaryStatistics est = personajes.stream()
                .filter(p -> p.getEpisodios() >= 2)
                .collect(Collectors.summarizingInt(Personaje::getEpisodios));

        System.out.println("Cantidad media de episodios: " + est.getAverage());
        System.out.println("Mayor cantidad de episodios: " + est.getMax());
        System.out.println("Menor cantidad de episodios: " + est.getMin());
        System.out.println("Cantidad de personajes evaluados: " + est.getCount());
    }
}
