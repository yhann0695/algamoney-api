package com.algaworks.algamoneyapi.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class RecursoCriadoEvent extends ApplicationEvent {

    @Getter
    private HttpServletResponse response;

    @Getter
    private Long codigo;

    public RecursoCriadoEvent(Object source, HttpServletResponse response, Long codigo) {
        super(source);
        this.response = response;
        this.codigo = codigo;
    }
}
