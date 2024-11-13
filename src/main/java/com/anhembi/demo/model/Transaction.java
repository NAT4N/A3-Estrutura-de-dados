package com.anhembi.demo.model;

import com.anhembi.demo.model.dto.TransactionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idTransacao;
    private String dataTransacao;
    private String idCliente;
    private String valor;
    private String tipo;
    @Column(columnDefinition = "JSON")
    private String produtos;


    public Transaction(String idCliente, String produtos, String valor, String tipo) {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.idTransacao = UUID.randomUUID().toString();
        this.dataTransacao = dt.format(LocalDateTime.now());
        this.idCliente = idCliente;
        this.valor = valor;
        this.tipo = tipo;
        this.produtos = produtos;
    }

    public TransactionDTO parseToTransactionDTO() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return new TransactionDTO(idTransacao, dataTransacao, idCliente, valor, List.of(objectMapper.readValue(produtos, Product[].class)), tipo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "{" +
                "\"idTransacao\":\"" + idTransacao + "\"" +

                ", \"dataTransacao\":\"" + dataTransacao + "\"" +

                ", \"idCliente\":\"" + idCliente + "\"" +

                ", \"valor\":\"" + valor + "\"" +

                ", \"tipo\":\"" + tipo + "\"" +

                ", \"produtos\":" + produtos  +

                "}";
    }
}
