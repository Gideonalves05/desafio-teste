package com.gideon.AlvesBank.operations;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class WithdrawalDto {

    private Double amount; // Valor da retirada
    private Long numberAccount; // Número da conta de quem fez a retirada
    private LocalDateTime timestamp; // Data e hora da retirada
}
