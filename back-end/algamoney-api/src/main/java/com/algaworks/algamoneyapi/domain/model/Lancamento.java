package com.algaworks.algamoneyapi.domain.model;

import com.algaworks.algamoneyapi.domain.model.enums.TipoLancamento;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "TB_LANCAMENTO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Lancamento implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CO_LANCAMENTO")
    private Long codigo;

    @NotNull
    @Column(name = "DS_LANCAMENTO")
    private String descricao;

    @NotNull
    @Column(name = "DT_VENCIMENTO")
    private LocalDate dataVencimento;

    @Column(name = "DT_PAGAMENTO")
    private LocalDate dataPagamento;

    @NotNull
    @Column(name = "DS_TIPO")
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;


    @NotNull
    @JoinColumn(name = "CO_PESSOA")
    @ManyToOne
    private Pessoa pessoa;


    @NotNull
    @JoinColumn(name = "CO_CATEGORIA")
    @ManyToOne
    private Categoria categoria;

    @NotNull
    @Column(name = "VL_LANCAMENTO")
    private BigDecimal valor;

    @Column(name = "OBS_LANCAMENTO")
    private String observacao;
}
