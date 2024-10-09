package com.gideon.AlvesBank.operations.statement;

import com.gideon.AlvesBank.users.UserModel;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "statement_tb") // Nome da tabela
public class Statement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private LocalDateTime timestamp;
    private String transactionType; // Pode ser "DEPOSIT", "WITHDRAWAL", "TRANSFER_IN", "TRANSFER_OUT"
    private Long numberAccount; // Número da conta que fez a operação
    private Long targetAccount; // Número da conta que recebeu a operação (null para depósitos e retiradas)

}
