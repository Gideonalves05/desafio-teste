package com.gideon.AlvesBank.operations.statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatementService {

    @Autowired
    private StatementRepository statementRepository;

    // Método para registrar uma nova transação no extrato
    public Statement createStatement(Long numberAccount, Double amount, String transactionType, Long targetAccount) {
        Statement statement = new Statement();
        statement.setNumberAccount(numberAccount);
        statement.setAmount(amount);
        statement.setTimestamp(LocalDateTime.now());
        statement.setTransactionType(transactionType);
        statement.setTargetAccount(targetAccount);
     

        return statementRepository.save(statement);
    }

    // Método para buscar todos os extratos de um usuário
    public List<Statement> getStatements(Long numberAccount) {
        return statementRepository.findByNumberAccount(numberAccount);
    }
}
