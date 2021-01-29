package com.algaworks.algamoneyapi.domain.repository.lancamento;

import com.algaworks.algamoneyapi.domain.model.Lancamento;
import com.algaworks.algamoneyapi.domain.repository.filter.LancamentoFilter;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ILancamentoRepositoryImpl implements ILancamentoRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Lancamento> filtrar(LancamentoFilter filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        Predicate[] predicates = criarRestricoes(filter, builder, root);
        criteria.where(predicates);

        TypedQuery<Lancamento> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    public Predicate[] criarRestricoes(LancamentoFilter filter, CriteriaBuilder builder, Root<Lancamento> root) {

        List<Predicate> predicates = new ArrayList<>();

        if(filter.getDescricao() != null) {
            predicates.add(builder.like(
                    builder.lower(root.get("descricao")), "%" + filter.getDescricao().toLowerCase() + "%"));
        }

        if(filter.getDataVencimentoDe() != null) {
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get("dataVencimento"), filter.getDataVencimentoDe()));
        }

        if(filter.getDataVencimentoAte() != null) {
            predicates.add(
                    builder.lessThanOrEqualTo(root.get("dataVencimento"), filter.getDataVencimentoAte()));
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
