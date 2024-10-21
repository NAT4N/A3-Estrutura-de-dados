package com.anhembi.demo.controller;

import com.anhembi.demo.model.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/transacao")
    public ResponseEntity transacao(@RequestParam int quantity) {
        return ResponseEntity.ok(transacaoService.populateList(quantity));
    }

    @PostMapping("/transacao/remove")
    public ResponseEntity removeTransacao() {
        return ResponseEntity.ok(transacaoService.removeTransacao());
    }

    @PostMapping("/transacao/pilha")
    public ResponseEntity transacaoPilha() {
        return ResponseEntity.ok(transacaoService.printPilha());
    }
}
