package com.algaworks.algamoneyapi.controller;

import com.algaworks.algamoneyapi.domain.model.Categoria;
import com.algaworks.algamoneyapi.domain.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> inserir(@Valid @RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.inserir(categoria));
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
