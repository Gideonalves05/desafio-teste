package com.gideon.AlvesBank.operations;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DepositDto {
    private Long numberAccount;
    private Double amount;
    private LocalDateTime timestamp;


    }

