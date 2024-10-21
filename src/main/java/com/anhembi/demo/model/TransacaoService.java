package com.anhembi.demo.model;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class TransacaoService {

    public static final TransactionList lista = new TransactionList();
    public static final Pilha pilha = new Pilha();
    Random random = new Random();


    public String populateList(int quantity) {
        for (int i = 0; i < quantity; i++) {
            addTransacao(new Transacao((long) i, LocalDateTime.now().toString(), random.nextDouble(), "Categoria", "Tipo", UUID.randomUUID().toString()));
        }

        return lista.toString();
    }

    public void addTransacao(Transacao transacao) {
        lista.insert(transacao);
    }

    public void addTransacaoPilha(Transacao transacao) {
        pilha.push(transacao);
    }

    public Transacao removeTransacao() {
        Transacao transacao = lista.removeFirst();
        addTransacaoPilha(transacao);
        return transacao;
    }

    public String printPilha() {
        return pilha.toString();
    }

}
