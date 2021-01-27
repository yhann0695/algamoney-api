package com.algaworks.algamoneyapi.domain.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PaginacaoDTO<F> implements Serializable {

    private Integer quantidadePorPagina;
    private Integer numeroPagina;
    private F filtro;
}
