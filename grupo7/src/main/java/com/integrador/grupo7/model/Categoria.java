package com.integrador.grupo7.model;


import javax.persistence.*;


@Entity
@Table(name="categorias")
public class Categoria {


    /* Atributos */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_categoria")
    private Long id;

    private String titulo;
    private String descripcion;
    private String urlImagen;

    /* Constructor */
    public Categoria() {
    }

    public Categoria(String titulo, String descripcion, String urlImagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
    }

    /* Getters y Setters */
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }


    /* Métodos */
    @Override
    public String toString() {
        return "Categoria " +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", urlImagen='" + urlImagen + '\'' +
                '.';
    }
}
