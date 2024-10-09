package com.gideon.AlvesBank.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long> {

    // Busca o usuário pelo número da conta
    UserModel findByNumberAccount(Long numberAccount);

    // Busca o usuário pelo CPF
    UserModel findByCpfAccount(String cpfAccount);
}
