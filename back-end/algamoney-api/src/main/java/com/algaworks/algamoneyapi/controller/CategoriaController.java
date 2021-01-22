package com.algaworks.algamoneyapi.controller;

import com.algaworks.algamoneyapi.domain.model.Categoria;
import com.algaworks.algamoneyapi.domain.service.CategoriaService;
import com.algaworks.algamoneyapi.event.RecursoCriadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping
    public ResponseEntity<Categoria> inserir(@Valid @RequestBody Categoria categoria,
                                             HttpServletResponse response) {
        categoriaService.inserir(categoria);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoria.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> buscarCategorias() {
        return ResponseEntity.ok(categoriaService.buscarTodos());
    }

    @GetMapping(value = "/{codigo}")
    public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
        return ResponseEntity.ok(categoriaService.buscarPeloCodigo(codigo));
    }


}
