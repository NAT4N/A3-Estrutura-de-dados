package com.anhembi.A3.repositories;

import com.anhembi.A3.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByIdCliente(String idCliente);
}
