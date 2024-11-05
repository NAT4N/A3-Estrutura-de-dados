package com.anhembi.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataTransacao;
    private String valor;
    private String categoria;
    private String tipo;
    private String transacaoHash;

    public Transacao(String valor, String categoria, String tipo) {
        this.dataTransacao = LocalDateTime.now().toString();
        this.valor = valor;
        this.categoria = categoria;
        this.tipo = tipo;
        this.transacaoHash = UUID.randomUUID().toString();
    }
}
