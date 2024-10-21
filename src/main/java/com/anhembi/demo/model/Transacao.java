package com.anhembi.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class Transacao {
    private Long id;
    private String dataTransacao;
    private Double valor;
    private String categoria;
    private String tipo;
    private String hash = UUID.randomUUID().toString();
}
