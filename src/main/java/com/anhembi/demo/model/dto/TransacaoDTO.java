package com.anhembi.demo.model.dto;

import com.anhembi.demo.model.Transacao;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@AllArgsConstructor
@Data
public class TransacaoDTO {
    private String valor;
    private String categoria;
    private String tipo;

    public Transacao parseToTransacao(){
        Random random = new Random();

        return new Transacao(random.nextLong(1, 99999999999L), valor,
                categoria, tipo);
    }
}
