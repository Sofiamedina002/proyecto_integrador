package com.integrador.grupo7.service;


import com.integrador.grupo7.model.Categoria;
import com.integrador.grupo7.repository.impl.ICategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CategoriaService implements IEntidadService<Categoria> {

    /* Atributos */
    private ICategoriaRepository categoriaRepository;
    java.util.logging.Logger logger = Logger.getLogger(String.valueOf(CategoriaService.class));

    /* Constructor */
    @Autowired
    public CategoriaService(ICategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    /* Métodos */
    @Override
    public Categoria save(Categoria categoria)  {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria update(Categoria categoriaNew) {
        Categoria cat = categoriaRepository.findById(categoriaNew.getId()).get();
        cat.setTitulo(categoriaNew.getTitulo());
        cat.setDescripcion(categoriaNew.getDescripcion());
        cat.setUrlImagen(categoriaNew.getUrlImagen());
        categoriaRepository.save(cat);
        logger.info("Ha sido actualizada la categoría correspondiente al ID: "+ cat.getId());
        return cat;
    }

    @Override
    public void delete(Long id) {
        if(categoriaRepository.findById(id).isPresent()){
            categoriaRepository.deleteById(id);
            logger.info("Registro eliminado de la entidad Categorías correctamente");
            System.out.println("Eliminado con éxito!");
        } else {
            logger.info("No ha sido posible eliminar el registro de la entidad Categorías: ID no encontrado");
            System.out.println("Categoría no encontrado!");
        }
    }


    public Optional<Categoria> findCategoriaByTitulo(String titulo) {
        logger.info("Búsqueda en la entidad Categorías filtrada por Título");
        return categoriaRepository.findCategoriaByTitulo(titulo);
    }

}
