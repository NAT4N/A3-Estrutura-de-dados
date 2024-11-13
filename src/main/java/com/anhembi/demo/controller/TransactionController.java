package com.anhembi.demo.controller;

import com.anhembi.demo.model.dto.TransactionDTO;
import com.anhembi.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class TransactionController {

    @Autowired
    private TransactionService transacaoService;

    @PostMapping("/transacao/populate")
    public ResponseEntity transacao(@RequestParam int quantity) {
        return ResponseEntity.ok(transacaoService.populateList(quantity));
    }

    @PostMapping("/transacao/new")
    public ResponseEntity newTransaction(@RequestBody TransactionDTO transacao) {
        System.out.println(transacao);
        return ResponseEntity.ok(transacaoService.newTransaction(transacao.parseToTransaction()));
    }

    @PostMapping("/transacao/remove")
    public ResponseEntity removeTransacao() {
        return ResponseEntity.ok(transacaoService.removeTransacao());
    }

    @PostMapping("/transacao/consolidar")
    public ResponseEntity<Object> consolidarTransacoes() {
        return ResponseEntity.ok(transacaoService.consolidarTransacao());
    }
}
