package com.algaworks.algamoneyapi.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "TB_PERMISSAO")
public class Permissao implements Serializable {

    @Id
    @Column(name = "CO_PERMISSAO")
    @EqualsAndHashCode.Include
    private Long codigo;

    @Column(name = "DS_PERMISSAO")
    private String descricao;
}
