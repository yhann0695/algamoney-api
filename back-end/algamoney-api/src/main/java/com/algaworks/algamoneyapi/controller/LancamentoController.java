package com.algaworks.algamoneyapi.controller;

import com.algaworks.algamoneyapi.domain.model.Lancamento;
import com.algaworks.algamoneyapi.domain.model.dto.PaginacaoDTO;
import com.algaworks.algamoneyapi.domain.repository.filter.LancamentoFilter;
import com.algaworks.algamoneyapi.domain.service.LancamentoService;
import com.algaworks.algamoneyapi.event.RecursoCriadoEvent;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.ws.Response;
import java.time.LocalDate;
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

    @GetMapping(value = "/{codigo}")
    public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo) {
       return ResponseEntity.ok(lancamentoService.buscarPeloCodigo(codigo));
    }

    @GetMapping
    public ResponseEntity<Page<Lancamento>> pesquisar(LancamentoFilter filter, Pageable pageable) {
        Page<Lancamento> lista = lancamentoService.filtrar(filter, pageable);
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping(value = "/{codigo}")
    public ResponseEntity<Long> exluir(@PathVariable(value = "codigo") Long codigo,
                                       HttpServletResponse response) {
        lancamentoService.excluir(codigo);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, codigo));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
