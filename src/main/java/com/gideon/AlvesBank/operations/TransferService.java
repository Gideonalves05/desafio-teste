package com.gideon.AlvesBank.operations;

import com.gideon.AlvesBank.users.UserModel;
import com.gideon.AlvesBank.users.IUserRepository;
import com.gideon.AlvesBank.operations.statement.StatementService; // Importa o StatementService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransferService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private StatementService statementService; // Injetando o StatementService

    public TransferDto transfer(Long fromAccount, Long toAccount, Double amount) {
        // Verifica se o valor da transferência é válido
        if (amount <= 0) {
            throw new IllegalArgumentException("O valor da transferência deve ser positivo.");
        }

        // Busca os usuários pelas contas
        UserModel fromUser = userRepository.findByNumberAccount(fromAccount);
        UserModel toUser = userRepository.findByNumberAccount(toAccount);
        if (fromUser == null || toUser == null) {
            throw new IllegalArgumentException("Uma das contas não foi encontrada.");
        }

        // Verifica se o remetente tem saldo suficiente
        if (fromUser.getBalance() < amount) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }

        // Atualiza os saldos
        fromUser.setBalance(fromUser.getBalance() - amount);
        toUser.setBalance(toUser.getBalance() + amount);

        // Salva os usuários com os saldos atualizados
        userRepository.save(fromUser);
        userRepository.save(toUser);

        // Cria registros no extrato
        statementService.createStatement(fromAccount, amount, "TRANSFER_OUT", toAccount);
        statementService.createStatement(toAccount, amount, "TRANSFER_IN", fromAccount);

        // Obtendo o timestamp atual
        LocalDateTime timestamp = LocalDateTime.now();
        TransferDto transferDto = new TransferDto();
        transferDto.setSenderAccount(fromAccount);
        transferDto.setRecipientAccount(toAccount);
        transferDto.setAmount(amount);
        transferDto.setTimestamp(timestamp); // Definindo o timestamp na transferência

        System.out.println("Transferência realizada com sucesso.");
        return transferDto; // Retorna o TransferDto
    }
}
