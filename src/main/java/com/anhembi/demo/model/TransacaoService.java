package com.anhembi.demo.model;

import com.anhembi.demo.exception.FilaVaziaException;
import com.anhembi.demo.exception.PilhaVaziaException;
import com.anhembi.demo.model.dto.TransacaoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TransacaoService {

    public static final TransactionList lista = new TransactionList();
    public static final Pilha pilha = new Pilha();
    Random random = new Random();


    public ResponseEntity<List<Transacao>> populateList(int quantity) {
        for (int i = 0; i < quantity; i++) {
            addTransaction(new TransacaoDTO(String.format("%.2f", random.nextDouble(0, 1000)), "Categoria", "Tipo").parseToTransacao());
        }

        return ResponseEntity.ok().body(lista.print());
    }
    public Transacao newTransaction(Transacao transaction)
    {
        addTransaction(transaction);
        return transaction;
    }

    public void addTransaction(Transacao transacao) {
        lista.insert(transacao);
    }

    public void addTransacaoPilha(Transacao transacao) {
        pilha.push(transacao);
    }

    @Scheduled(fixedRate = 1000)
    public Transacao removeTransacao() {
        Transacao transacao = lista.removeFirst();

        if(transacao == null) {
            //throw new FilaVaziaException("Fila vazia ! Nao ha transacoes para processar");
            System.out.println("Fila vazia ! Nao ha transacoes para processar");
            return null;
        }

        addTransacaoPilha(transacao);
        System.out.println("Transacao Processada: " + transacao);
        return transacao;
    }

    public String printPilha() {
        return pilha.toString();
    }

    public List<Transacao> consolidarTransacao()
    {
        List<Transacao> pilhaConsolidada = new ArrayList<>();
        System.out.println("CONSOLIDANDO TRANSACOES");

        if (pilha.getTop() == null)
        {
            throw new PilhaVaziaException("Pilha vazia ! Nao ha transacoes para consolidar");
        }

        while (pilha.getTop() != null)
        {
            pilhaConsolidada.add(pilha.pop());
        }

        System.out.println("TRANSACOES CONSOLIDADAS");
        System.out.println("Pilha agora: " + pilha);
        return pilhaConsolidada;
    }

}
