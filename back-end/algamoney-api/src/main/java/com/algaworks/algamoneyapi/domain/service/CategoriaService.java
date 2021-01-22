package com.algaworks.algamoneyapi.domain.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.algaworks.algamoneyapi.domain.exceptions.NotFoundException;
import com.algaworks.algamoneyapi.domain.model.Categoria;
import com.algaworks.algamoneyapi.domain.repository.ICategoriaRepository;
import com.algaworks.algamoneyapi.utils.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private ICategoriaRepository repository;

    @Transactional
    public Categoria inserir(Categoria categoria) {
        return repository.save(categoria);
    }

    @Transactional
    public List<Categoria> buscarTodos() {
        return repository.findAll();
    }

    @Transactional
    public Categoria buscarPeloCodigo(Long codigo) {
        Optional<Categoria> categoria = repository.findById(codigo);
        if(!categoria.isPresent()) {
            throw new NotFoundException(Mensagens.MSG_CODIGO_INEXISTENTE);
        }
        return categoria.get();
    }
}
