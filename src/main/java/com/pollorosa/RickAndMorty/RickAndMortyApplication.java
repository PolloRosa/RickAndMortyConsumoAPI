package com.pollorosa.RickAndMorty;

import com.pollorosa.RickAndMorty.vista.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RickAndMortyApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RickAndMortyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal aplicacion = new Principal();
		aplicacion.iniciar();
	}
}
