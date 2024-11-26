package com.anhembi.A3.controller;

import com.anhembi.A3.model.dto.TransactionDTO;
import com.anhembi.A3.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionService transacaoService;

    @PostMapping("/transacao/populate")
    public ResponseEntity<Object> transacao(@RequestParam int quantity) {
        return ResponseEntity.ok(transacaoService.populateList(quantity));
    }

    @PostMapping("/transacao/nova")
    public ResponseEntity<Object> newTransaction(@RequestBody TransactionDTO transacao) {
        System.out.println(transacao);
        return ResponseEntity.ok(transacaoService.newTransaction(transacao.parseToTransaction()));
    }

    @PostMapping("/transacao/processar")
    public ResponseEntity<Object> removeTransacao() {
        return ResponseEntity.ok(transacaoService.processarTransacao());
    }

    @GetMapping("/transacao/consultar")
    public ResponseEntity<Object> consolidarTransacoes(@RequestParam String clientId) {
        return ResponseEntity.ok(transacaoService.consultarTransacao(clientId));
    }
}
