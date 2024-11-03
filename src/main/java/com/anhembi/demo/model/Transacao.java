package com.anhembi.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Transacao {

    private Long id;
    private String dataTransacao;
    private String valor;
    private String categoria;
    private String tipo;
    private String hash;

    public Transacao(Long id, String valor, String categoria, String tipo) {
        this.id = id;
        this.dataTransacao = LocalDateTime.now().toString();
        this.valor = valor;
        this.categoria = categoria;
        this.tipo = tipo;
        this.hash = UUID.randomUUID().toString();
    }
}
