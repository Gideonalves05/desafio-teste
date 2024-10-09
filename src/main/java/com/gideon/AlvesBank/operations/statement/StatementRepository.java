package com.gideon.AlvesBank.operations.statement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Long> {
    List<Statement> findByNumberAccount(Long numberAccount); // Para buscar todos os extratos de uma conta
}
