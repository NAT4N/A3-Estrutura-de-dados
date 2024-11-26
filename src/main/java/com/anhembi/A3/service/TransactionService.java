package com.anhembi.A3.service;

import com.anhembi.A3.model.Product;
import com.anhembi.A3.model.Transaction;
import com.anhembi.A3.model.TransactionQueue;
import com.anhembi.A3.model.TransactionStack;
import com.anhembi.A3.model.dto.TransactionDTO;
import com.anhembi.A3.model.dto.UserDTO;
import com.anhembi.A3.repositories.TransacaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TransactionService {

    public static final TransactionQueue fila = new TransactionQueue();
    public static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public TransacaoRepository repository;
    Random random = new Random();


    public List<Transaction> populateList(int quantity) {
        for (int i = 0; i < quantity; i++) {
            fila.insert(new Transaction("ASJHDBA-897IKJHBK-AHSBD787", "[{\"name\": \"Prod1\",\"description\": \"asdasd\",\"price\": 10.22,\"quantity\": 5}]", String.format("%.2f", random.nextDouble(0, 100)), "Tipo"));
        }

        return fila.print();
    }
    public TransactionDTO newTransaction(Transaction transaction)
    {
        fila.insert(transaction);
        return transaction.parseToTransactionDTO();
    }


    @Scheduled(fixedRate = 1000)
    public Transaction removeTransacao() {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        Transaction transaction = fila.removeFirst();

        if(transaction == null) {
            System.out.println("Fila vazia ! Nao ha transacoes para processar");
            return null;
        }

        transaction.setDataTransacao(dt.format(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))));

        repository.save(transaction);

        System.out.println("Transacao Processada: " + transaction);
        return transaction;
    }

    public Object consultarTransacao(String clientId) {
        try {
            final TransactionStack pilha = new TransactionStack();

            List<Transaction> transacoes = repository.findAllByIdCliente(clientId);

            transacoes.forEach(t -> {
                try {
                    pilha.push(new TransactionDTO(t.getIdTransacao(), t.getDataTransacao(), t.getIdCliente(), t.getValor(), List.of(
                            mapper.readValue(t.getProdutos(), Product[].class)),
                            t.getTipo()).parseToTransaction());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            return mapper.readValue(pilha.toString(), Object.class);

        }catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
    }

}
