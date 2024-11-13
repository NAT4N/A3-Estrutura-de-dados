package com.anhembi.demo.model.dto;

import com.anhembi.demo.model.Product;
import com.anhembi.demo.model.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class TransactionDTO {

    private String idTransacao;
    private String dataTransacao;
    private String idCliente;
    private String valor;
    private List<Product> produtos;
    private String tipo;

    public Transaction parseToTransaction() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Transaction result = new Transaction(idCliente, objectMapper.writeValueAsString(produtos), valor, tipo);
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}