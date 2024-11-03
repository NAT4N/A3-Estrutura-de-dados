package com.anhembi.demo.controller;

import com.anhembi.demo.model.Transacao;
import com.anhembi.demo.model.TransacaoService;
import com.anhembi.demo.model.dto.TransacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/transacao/populate")
    public ResponseEntity transacao(@RequestParam int quantity) {
        return ResponseEntity.ok(transacaoService.populateList(quantity));
    }

    @PostMapping("/transacao/new")
    public ResponseEntity newTransaction(@RequestBody TransacaoDTO transacao) {
        return ResponseEntity.ok(transacaoService.newTransaction(transacao.parseToTransacao()));
    }
    @PostMapping("/transacao/remove")
    public ResponseEntity removeTransacao() {
        return ResponseEntity.ok(transacaoService.removeTransacao());
    }

    @PostMapping("/transacao/pilha")
    public ResponseEntity transacaoPilha() {
        return ResponseEntity.ok(transacaoService.printPilha());
    }

    @PostMapping("/transacao/consolidar")
    public ResponseEntity<List<Transacao>> consolidarTransacoes()
    {
        return ResponseEntity.ok(transacaoService.consolidarTransacao());
    }
}
