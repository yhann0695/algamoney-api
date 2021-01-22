package com.algaworks.algamoneyapi.controller;

import com.algaworks.algamoneyapi.domain.model.Pessoa;
import com.algaworks.algamoneyapi.domain.service.PessoaService;
import com.algaworks.algamoneyapi.event.RecursoCriadoEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping
    public ResponseEntity<Pessoa> inserir(@Valid @RequestBody Pessoa pessoa,
                                          HttpServletResponse response) {
        pessoaService.inserir(pessoa);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoa.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> buscarTodos() {
        return ResponseEntity.ok(pessoaService.buscarTodos());
    }

    @GetMapping(value = "/{codigo}")
    public ResponseEntity<Pessoa> buscarPorCodigo(@PathVariable Long codigo) {
        return ResponseEntity.ok(pessoaService.buscarPorCodigo(codigo));
    }

    @DeleteMapping(value = "/{codigo}")
    public ResponseEntity<Long> excluir(@PathVariable Long codigo,
                                     HttpServletResponse response) {
        pessoaService.excluir(codigo);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, codigo));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(codigo);
    }

    @PutMapping(value = "/{codigo}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo,  @Valid @RequestBody Pessoa pessoa,
                                            HttpServletResponse response) {
        Pessoa pessoaAtualizada = pessoaService.atualizar(pessoa, codigo);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoa.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaAtualizada);
    }

    @PutMapping(value = "/{codigo}/ativo")
    public ResponseEntity<Pessoa> atualizarPropriedadeAtivo(@PathVariable Long codigo,
                                @RequestBody(required = true) Boolean ativo, HttpServletResponse response) {
        Pessoa pessoaAtiva = pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaAtiva.getCodigo()));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(pessoaAtiva);
    }
}
