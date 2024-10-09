package com.gideon.AlvesBank.operations.statement;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StatementDto {
    private Double amount;
    private LocalDateTime timestamp;
    private String transactionType;
    private Long numberAccount;
    private Long targetAccount; // null se não houver
    private Long cpfAccount;
}
