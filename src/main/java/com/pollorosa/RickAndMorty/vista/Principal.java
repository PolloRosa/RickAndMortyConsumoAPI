package com.pollorosa.RickAndMorty.vista;

import com.pollorosa.RickAndMorty.model.Constantes;
import com.pollorosa.RickAndMorty.model.DatosPersonaje;
import com.pollorosa.RickAndMorty.model.DatosResultado;
import com.pollorosa.RickAndMorty.service.ConsultaDatos;

import java.util.ArrayList;
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

        List<DatosPersonaje> personajes = resultados.stream()
                .flatMap(r -> r.personajes().stream())
                .collect(Collectors.toList());

        personajes//.stream()
                //.filter(p -> p.especie().equalsIgnoreCase("human"))
                //.limit(10)
                .forEach(System.out::println);


    }
}
