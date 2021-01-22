package com.algaworks.algamoneyapi.domain.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String mensagem) {
        super(mensagem);
    }

}
