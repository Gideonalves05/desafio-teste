package com.gideon.AlvesBank.users.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long numberAccount;
    private String firstName;
    private String lastName;
    private String cpfAccount;
    private Double balance;
    private String password;
}
