package com.algaworks.algamoneyapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "TB_PESSOA")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CO_PESSOA")
    @EqualsAndHashCode.Include
    private Long codigo;

    @NotNull
    @Size(min = 3, max = 30)
    @Column(name = "NO_PESSOA")
    private String nome;

    @Embedded
    private Endereco endereco;

    @NotNull
    @Column(name = "IC_PESSOA")
    private Boolean ativo;

    @JsonIgnore
    @Transient
    public boolean isInativo() { return !this.ativo; }
}
