package com.algaworks.algamoneyapi.domain.repository;

import com.algaworks.algamoneyapi.domain.model.Lancamento;
import com.algaworks.algamoneyapi.domain.repository.lancamento.ILancamentoRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ILancamentoRepository extends JpaRepository<Lancamento, Long>, ILancamentoRepositoryQuery {

    List<Lancamento> findByDescricaoContainsIgnoreCase(String descricao);

    Page<Lancamento> findByDescricaoContainsIgnoreCase(Pageable paginacao, String filtro);


    // List<Lancamento> findByDescricaoContainsIgnoreCase(String descricao);

   // List<Lancamento> findByDataVencimentoGreaterThanEqualAndTimeStampLessThanEqual(
   //         LocalDate dataVencimentoDe);

   // List<Lancamento> findByDescricaoAndDataVencimentoGreaterThanEqual(String descricao, LocalDate dataVencimento);
}
