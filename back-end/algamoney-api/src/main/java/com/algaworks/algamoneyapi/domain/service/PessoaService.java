package com.algaworks.algamoneyapi.domain.service;

import com.algaworks.algamoneyapi.domain.exceptions.NotFoundException;
import com.algaworks.algamoneyapi.domain.model.Pessoa;
import com.algaworks.algamoneyapi.domain.repository.IPessoaRepository;
import com.algaworks.algamoneyapi.utils.Mensagens;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private IPessoaRepository repository;

    @Transactional
    public Pessoa inserir(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    @Transactional
    public List<Pessoa> buscarTodos() {
        return repository.findAll();
    }

    public Pessoa buscarPorCodigo(Long codigo) {
        Optional<Pessoa> pessoa = repository.findById(codigo);
        if (!pessoa.isPresent()) {
            throw new NotFoundException(Mensagens.MSG_CODIGO_INEXISTENTE);
        }
        return pessoa.get();
    }

    @Transactional
    public Long excluir(Long codigo) {
        Optional<Pessoa> pessoa = repository.findById(codigo);
        if (!pessoa.isPresent()) {
            throw new NotFoundException(Mensagens.MSG_CODIGO_INEXISTENTE);
        }
        repository.delete(pessoa.get());
        return codigo;
    }

    @Transactional
    public Pessoa atualizar(Pessoa pessoa, Long codigo) {
        Pessoa pessoaSalva = this.verificarCodigoPessoa(codigo);
        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
        return repository.save(pessoaSalva);
    }

    @Transactional
    public Pessoa atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Pessoa pessoaSalva = this.verificarCodigoPessoa(codigo);
        pessoaSalva.setAtivo(ativo);
        return repository.save(pessoaSalva);
    }

    private Pessoa verificarCodigoPessoa(Long codigo) {
        Pessoa pessoaSalva = repository.findById(codigo).get();
        if(pessoaSalva == null) {
            throw new NotFoundException(Mensagens.MSG_CODIGO_INEXISTENTE);
        }
        return pessoaSalva;
    }
}
