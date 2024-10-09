package com.gideon.AlvesBank.operations;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransferDto {
    private Long senderAccount;  // Conta de quem está enviando o dinheiro
    private Long recipientAccount;  // Conta de quem está recebendo o dinheiro
    private Double amount;  // Valor da transferência
    private LocalDateTime timestamp; // Data e hora da transferência
}
