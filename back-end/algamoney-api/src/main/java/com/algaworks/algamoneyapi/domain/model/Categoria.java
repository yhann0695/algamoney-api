package com.algaworks.algamoneyapi.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "TB_CATEGORIA")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "CO_CATEGORIA")
    private Long codigo;

    @Column(name = "NO_CATEGORIA")
    @NotNull
    @Size(min = 3, max = 20)
    private String nome;
}
