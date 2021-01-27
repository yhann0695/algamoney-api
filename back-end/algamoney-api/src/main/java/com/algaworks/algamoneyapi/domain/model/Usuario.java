package com.algaworks.algamoneyapi.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "TB_USUARIO")
public class Usuario implements Serializable {

    @Id
    @Column(name = "CO_USUARIO")
    @EqualsAndHashCode.Include
    private Long Codigo;

    @Column(name = "NO_USUARIO")
    private String nome;

    @Column(name = "EMAIL_USUARIO")
    private String email;

    @Column(name = "SENHA_USUARIO")
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TB_USUARIO_PERMISSAO", joinColumns = @JoinColumn(name = "CO_USUARIO"),
                        inverseJoinColumns = @JoinColumn(name = "CO_PERMISSAO"))
    private List<Permissao> permissao;
}
