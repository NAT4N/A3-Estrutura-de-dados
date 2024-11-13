package com.anhembi.demo.repositories;

import com.anhembi.demo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByIdCliente(String idCliente);
}
