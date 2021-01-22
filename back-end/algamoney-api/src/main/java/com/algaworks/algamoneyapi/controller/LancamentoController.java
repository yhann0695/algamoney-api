package com.algaworks.algamoneyapi.controller;

import com.algaworks.algamoneyapi.domain.model.Lancamento;
import com.algaworks.algamoneyapi.domain.service.LancamentoService;
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
@RequestMapping(value = "/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping
    public ResponseEntity<Lancamento> inserir(@Valid @RequestBody Lancamento lancamento,
                                              HttpServletResponse response) {

        lancamentoService.inserir(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamento.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamento);
    }

    @GetMapping
    public ResponseEntity<List<Lancamento>> buscarTodos() {
        return ResponseEntity.ok(lancamentoService.buscarTodos());
    }

    @GetMapping(value = "/{codigo}")
    public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo) {
       return ResponseEntity.ok(lancamentoService.buscarPeloCodigo(codigo));
    }
}
