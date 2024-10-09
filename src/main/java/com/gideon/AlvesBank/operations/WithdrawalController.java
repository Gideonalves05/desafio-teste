package com.gideon.AlvesBank.operations;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/withdrawal")
public class WithdrawalController {

    @Autowired
    private WithdrawalService withdrawService;
    @PostMapping()
    public ResponseEntity<?> withdraw( @RequestBody WithdrawalDto withdrawDto) {
        if(withdrawDto.getNumberAccount() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Número da conta é obrigatório");
        }
        
        // Validação manual
        if (withdrawDto.getAmount() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valor da retirada é obrigatório");
        }
        if (withdrawDto.getAmount() < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O valor da retirada deve ser maior que zero");
        }
    
        try {
            LocalDateTime timestamp = withdrawService.executeWithdrawal( withdrawDto.getNumberAccount(), withdrawDto.getAmount());
            withdrawDto.setTimestamp(timestamp); // Definindo o timestamp no WithdrawDto
            return ResponseEntity.status(HttpStatus.OK).body(withdrawDto); // Retornando o WithdrawDto com timestamp
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    }
