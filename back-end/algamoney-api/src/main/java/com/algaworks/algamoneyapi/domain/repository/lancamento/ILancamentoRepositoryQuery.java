package com.algaworks.algamoneyapi.domain.repository.lancamento;

import com.algaworks.algamoneyapi.domain.model.Lancamento;
import com.algaworks.algamoneyapi.domain.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ILancamentoRepositoryQuery {

    public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable);
}
