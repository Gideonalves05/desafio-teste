package com.gideon.AlvesBank.operations.statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statements")
public class StatementController {

    @Autowired
    private StatementService statementService;

    @PostMapping("/send")
    public ResponseEntity<Statement> createStatement(@RequestBody StatementDto statementDto) {
        Statement statement = statementService.createStatement(
                statementDto.getNumberAccount(),
                statementDto.getAmount(),
                statementDto.getTransactionType(),
                statementDto.getTargetAccount()
        );
        return ResponseEntity.ok(statement);
    }

    @PostMapping()
    public ResponseEntity<List<Statement>> getStatements(@RequestBody StatementDto statementDto) {
        Long numberAccount = statementDto.getNumberAccount();
        
        List<Statement> statements = statementService.getStatements(numberAccount);
        return ResponseEntity.ok(statements);
    }
}
