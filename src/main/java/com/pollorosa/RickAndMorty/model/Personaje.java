package com.pollorosa.RickAndMorty.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Personaje {
    private Integer id;
    private String nombre;
    private String estado;
    private String especie;
    private String tipo;
    private String genero;
    private String origen;
    private String ubicacion;
    private Integer episodios;
    private LocalDate fechaDeCreacion;

    public Personaje(DatosPersonaje datos) {
        this.id = datos.id();
        this.nombre = datos.nombre();
        this.estado = datos.estado();
        this.especie = datos.especie();
        this.tipo = datos.tipo();
        this.genero = datos.genero();
        this.origen = datos.origen().nombre();
        this.ubicacion = datos.ubicacion().nombre();
        this.episodios = datos.episodios().size();
        try {
            this.fechaDeCreacion = LocalDate.parse(datos.fechaDeCreacion().substring(0, 10));
        } catch (DateTimeParseException e) {
            this.fechaDeCreacion = null;
        }
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEstado() {
        return estado;
    }

    public String getEspecie() {
        return especie;
    }

    public String getTipo() {
        return tipo;
    }

    public String getGenero() {
        return genero;
    }

    public String getOrigen() {
        return origen;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public Integer getEpisodios() {
        return episodios;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    @Override
    public String toString() {
        return "Personaje: " +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", estado='" + estado + '\'' +
                ", especie='" + especie + '\'' +
                ", tipo='" + tipo + '\'' +
                ", genero='" + genero + '\'' +
                ", origen='" + origen + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", episodios=" + episodios +
                ", fechaDeCreacion=" + fechaDeCreacion;
    }
}
