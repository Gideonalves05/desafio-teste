package com.gideon.AlvesBank.operations;

import com.gideon.AlvesBank.users.UserModel;
import com.gideon.AlvesBank.users.IUserRepository;
import com.gideon.AlvesBank.operations.statement.StatementService; // Importa o StatementService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WithdrawalService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private StatementService statementService; // Injetando o StatementService

    public LocalDateTime executeWithdrawal(Long numberAccount, Double amount) {
        // Verifica se o valor da retirada é válido
        if (amount <= 0) {
            throw new IllegalArgumentException("O valor da retirada deve ser positivo.");
        }

        // Busca o usuário pela conta
        UserModel user = userRepository.findByNumberAccount(numberAccount);
        if (user == null) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }

        // Verifica se o usuário tem saldo suficiente
        if (user.getBalance() < amount) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }

        // Subtrai o valor do saldo da conta
        user.setBalance(user.getBalance() - amount);

        // Salva o usuário com o saldo atualizado
        userRepository.save(user);

        // Cria registro no extrato
        statementService.createStatement(numberAccount, amount, "WITHDRAWAL", null);

        LocalDateTime timestamp = LocalDateTime.now(); // Obtendo o timestamp atual
        System.out.println("Retirada realizada com sucesso.");
        return timestamp; // Retorna o timestamp
    }
}
