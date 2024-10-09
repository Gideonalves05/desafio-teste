package com.gideon.AlvesBank.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping()
    public ResponseEntity<?> transfer(@RequestBody TransferDto transferDto) {
        if (transferDto.getSenderAccount() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Número da conta do remetente é obrigatório");
            
        }
        // Validação manual
        if (transferDto.getRecipientAccount() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Número da conta do destinatário é obrigatório");
        }
        if (transferDto.getAmount() == null || transferDto.getAmount() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valor da transferência é obrigatório e deve ser positivo");
        }
    
        try {
            TransferDto result = transferService.transfer(transferDto.getSenderAccount(), transferDto.getRecipientAccount(), transferDto.getAmount());
            return ResponseEntity.status(HttpStatus.OK).body(result); // Retornando o TransferDto com timestamp
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
}
