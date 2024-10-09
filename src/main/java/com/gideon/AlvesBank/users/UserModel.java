package com.gideon.AlvesBank.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_users")
public class UserModel {

    @Id
    @GeneratedValue(generator = "NumberAccount")
    @Column(unique = true)
    private Long numberAccount = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;


    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String cpfAccount;
    
    private String password;

   

    @CreationTimestamp
    private LocalDateTime createdTa;

    private Double balance;

    public UserModel() {
        this.balance = 0.0; // Inicializa o saldo com 0.0
    }

    public Double getBalance() {
        if (this.balance == null) {
            this.balance = 0.0; // Inicializa se for nulo
        }
        return this.balance;
    }

    public void addBalance(Double amount) {
        // Verifica se o saldo é nulo e inicializa se for
        if (this.balance == null) {
            this.balance = 0.0; // Garante que o saldo seja inicializado
        }
        this.balance += amount; // Adiciona o valor ao saldo
    }


    public void subtractBalance(Double amount) {
        // Verifica se o saldo é nulo e inicializa caso seja
        if (this.balance == null) {
            this.balance = 0.0;
        }
        // Certifique-se de que o saldo não fique negativo
        if (this.balance >= amount) {
            this.balance -= amount; // Subtrai o valor do saldo
        } else {
            throw new IllegalArgumentException("Saldo insuficiente para a operação.");
        }
    }

    // Outros métodos e lógica adicionais, se necessário
}
