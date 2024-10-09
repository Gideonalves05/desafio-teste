package com.gideon.AlvesBank.operations;

import com.gideon.AlvesBank.users.UserModel;
import com.gideon.AlvesBank.users.IUserRepository;
import com.gideon.AlvesBank.operations.statement.StatementService; // Importa o StatementService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DepositService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private StatementService statementService; // Injetando o StatementService

    public LocalDateTime deposit(Long numberAccount, Double amount) {
        // Verifica se o valor do depósito é válido
        if (amount <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser positivo.");
        }

        // Busca o usuário pela conta
        UserModel user = userRepository.findByNumberAccount(numberAccount);
        if (user == null) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }

        // Se balance for nulo, inicialize
        if (user.getBalance() == null) {
            user.setBalance(0.0);
        }

        // Adiciona o valor ao saldo da conta
        user.addBalance(amount); // Use o método addBalance

        // Salva o usuário com o saldo atualizado
        userRepository.save(user);

        // Cria registro no extrato
        statementService.createStatement(numberAccount, amount, "DEPOSIT", null);

        LocalDateTime timestamp = LocalDateTime.now(); // Obtendo o timestamp atual
        System.out.println("Depósito realizado com sucesso.");
        return timestamp; // Retorna o timestamp
    }
}
