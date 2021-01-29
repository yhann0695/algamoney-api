package com.algaworks.algamoneyapi.domain.repository.lancamento;

import com.algaworks.algamoneyapi.domain.model.Lancamento;
import com.algaworks.algamoneyapi.domain.repository.filter.LancamentoFilter;

import java.util.List;

public interface ILancamentoRepositoryQuery {

    public List<Lancamento> filtrar(LancamentoFilter filter);
}
