package com.integrador.grupo7.controller;



import com.integrador.grupo7.dto.CategoriaDTO;
import com.integrador.grupo7.exception.BadRequestException;
import com.integrador.grupo7.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    /* Atributos */
    private CategoriaService categoriaService;
    Logger logger = Logger.getLogger(String.valueOf(CategoriaController.class));

    /* Constructor */
    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    /* GET */
    @GetMapping("/{id}")
    public CategoriaDTO findById(@PathVariable Long id) {
        logger.info("Búsqueda de registro por id en la entidad Categorías");

            return categoriaService.findById(id).orElse(null);
    }

    @GetMapping("/{titulo}")
    public CategoriaDTO findCategoriaByTitulo(@PathVariable String titulo) {
        logger.info("Búsqueda de registro por título en la entidad Categorías");
        return categoriaService.findCategoriaByTitulo(titulo);
    }

    @GetMapping()
    public List<CategoriaDTO> findAll() {
        logger.info("Listado de Categorías");
        return categoriaService.findAll();
    }

    /* POST */
    @PostMapping("/registrar")
    public ResponseEntity save(@RequestBody CategoriaDTO categoria) {
        ResponseEntity response;
        if(categoriaService.findCategoriaByTitulo(categoria.getTitulo()) != null) {
            response = new ResponseEntity("La categoría ingresada ya se encuentra registrada!", HttpStatus.CONFLICT);
        } else {
            response = new ResponseEntity(categoriaService.save(categoria), HttpStatus.OK);
        }
        return response;
    }

    /* PUT */
    @PutMapping("/modificar")
    public ResponseEntity updateCategoria(@RequestBody CategoriaDTO modificacion) throws BadRequestException {
        ResponseEntity respuesta;

        if(categoriaService.findById(modificacion.getId()).isPresent()) {
            respuesta = new ResponseEntity(categoriaService.update(modificacion), HttpStatus.OK);
        } else {
            logger.info("Listado de Categorías");
            respuesta = new ResponseEntity("Categoría no encontrada!", HttpStatus.NOT_FOUND);
        }
        return respuesta;
    }


    /* DELETE */
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity deleteCategoria(@PathVariable Long id) {
        ResponseEntity respuesta;

        if(categoriaService.findById(id).isPresent()) {
            categoriaService.delete(id);
            respuesta = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Categoría eliminada correctamente!");
        } else {
            respuesta = new ResponseEntity("Categoría no encontrada!", HttpStatus.NOT_FOUND);
        }
        return respuesta;
    }

}
