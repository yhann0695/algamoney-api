package com.algaworks.algamoneyapi.domain.repository.filter;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class LancamentoFilter {

    private String descricao;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataVencimentoDe;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataVencimentoAte;
}
