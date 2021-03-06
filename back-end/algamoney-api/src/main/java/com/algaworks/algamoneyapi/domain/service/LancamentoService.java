package com.algaworks.algamoneyapi.domain.service;

import com.algaworks.algamoneyapi.domain.exceptions.NotFoundException;
import com.algaworks.algamoneyapi.domain.exceptions.PessoaInexistenteOuInativoException;
import com.algaworks.algamoneyapi.domain.model.Lancamento;
import com.algaworks.algamoneyapi.domain.model.Pessoa;
import com.algaworks.algamoneyapi.domain.model.dto.PaginacaoDTO;
import com.algaworks.algamoneyapi.domain.repository.ILancamentoRepository;
import com.algaworks.algamoneyapi.domain.repository.IPessoaRepository;
import com.algaworks.algamoneyapi.domain.repository.filter.LancamentoFilter;
import com.algaworks.algamoneyapi.utils.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private ILancamentoRepository repository;

    @Autowired
    private IPessoaRepository pessoaRepository;

    @Transactional
    public Lancamento inserir(Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo()).get();
        if(pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativoException();
        }
        return repository.save(lancamento);
    }

    @Transactional
    public Lancamento buscarPeloCodigo(Long codigo) {
        Optional<Lancamento>  optLancamento = repository.findById(codigo);
        if (!optLancamento.isPresent()) {
            throw new NotFoundException(Mensagens.MSG_CODIGO_INEXISTENTE);
        }
        return optLancamento.get();
    }

    @Transactional
    public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable) {
        return repository.filtrar(filter, pageable);
    }



    @Transactional
    public Long excluir(Long codigo) {
        Optional<Lancamento> lancamento = repository.findById(codigo);
        if(!lancamento.isPresent()) {
            throw new PessoaInexistenteOuInativoException();
        }
        repository.delete(lancamento.get());
        return codigo;
    }
}
