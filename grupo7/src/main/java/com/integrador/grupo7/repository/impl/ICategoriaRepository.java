package com.integrador.grupo7.repository.impl;

import com.integrador.grupo7.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("SELECT c FROM Categoria c WHERE c.titulo = ?1")
    Optional<Categoria> findCategoriaByTitulo(String titulo);
}
