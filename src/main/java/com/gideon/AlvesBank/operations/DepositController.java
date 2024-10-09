package com.gideon.AlvesBank.operations;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deposit")
public class DepositController {

    @Autowired
    private DepositService depositService;

@PostMapping()
public ResponseEntity<?> deposit( @RequestBody DepositDto depositDto) {
    if(depositDto.getNumberAccount() == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Número da conta é obrigatório");
    }
    // Validação manual
    if (depositDto.getAmount() == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valor do depósito é obrigatório");
    }
    if (depositDto.getAmount() < 0) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O valor do depósito deve ser maior ou igual a zero");
    }

    try {
        LocalDateTime timestamp = depositService.deposit(depositDto.getNumberAccount(), depositDto.getAmount());
        depositDto.setTimestamp(timestamp); // Definindo o timestamp no DepositDto
        return ResponseEntity.status(HttpStatus.OK).body(depositDto); // Retornando o DepositDto com timestamp
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

}
