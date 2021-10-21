package com.integrador.grupo7.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.integrador.grupo7.dto.CategoriaDTO;
import com.integrador.grupo7.model.Categoria;
import com.integrador.grupo7.repository.impl.ICategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CategoriaService implements IEntidadService<CategoriaDTO> {

    /* Atributos */
    private ICategoriaRepository categoriaRepository;
    java.util.logging.Logger logger = Logger.getLogger(String.valueOf(CategoriaService.class));
    private ObjectMapper mapper;


    /* Constructor */
    @Autowired
    public CategoriaService(ICategoriaRepository categoriaRepository, ObjectMapper mapper) {
        this.categoriaRepository = categoriaRepository;
        this.mapper = mapper;
    }


    /* Métodos */
    @Override
    public CategoriaDTO save(CategoriaDTO categoria)  {
        categoriaRepository.save(mapper.convertValue(categoria, Categoria.class));
        return categoria;
    }

    @Override
    public Optional<CategoriaDTO> findById(Long id) {
        CategoriaDTO catDTO = null;
        Optional<Categoria> cat = categoriaRepository.findById(id);
        if(cat.isPresent()) {
            catDTO = mapper.convertValue(cat,CategoriaDTO.class);
        }
        return Optional.ofNullable(catDTO);
    }


    @Override
    public List<CategoriaDTO> findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaDTO> categoriasDTO = new ArrayList<>();
        for(Categoria cat : categorias) {
            categoriasDTO.add(mapper.convertValue(cat, CategoriaDTO.class));
        }
        return categoriasDTO;
    }

    @Override
    public CategoriaDTO update(CategoriaDTO categoriaNew) {
        Categoria cat = categoriaRepository.findById(categoriaNew.getId()).get();
        cat.setTitulo(categoriaNew.getTitulo());
        cat.setDescripcion(categoriaNew.getDescripcion());
        cat.setUrlImagen(categoriaNew.getUrlImagen());
        categoriaRepository.save(cat);
        logger.info("Ha sido actualizada la categoría correspondiente al ID: "+ cat.getId());
        return mapper.convertValue(cat, CategoriaDTO.class);
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


    public CategoriaDTO findCategoriaByTitulo(String titulo) {
        logger.info("Búsqueda en la entidad Categorías filtrada por Título");

        CategoriaDTO catDTO = null;
        Optional<Categoria> cat = categoriaRepository.findCategoriaByTitulo(titulo);
        if(cat != null) {
            catDTO = mapper.convertValue(cat, CategoriaDTO.class);
        }
        return catDTO;
    }

}
