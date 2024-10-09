package com.gideon.AlvesBank.users;

import com.gideon.AlvesBank.users.Dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    // Rota para criar uma nova conta de usuário
    @PostMapping("/createAccount")
    public ResponseEntity<?> create(@RequestBody UserModel userModel) {
        System.out.println(userModel);
        var user = this.userRepository.findByCpfAccount(userModel.getCpfAccount());
        
        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe!");
        }

        var userCreated = this.userRepository.save(userModel);
        System.out.println(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }

    // Rota para realizar login
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody String numberAccount, @RequestBody String cpfAccount, @RequestBody String password) {
    var user = this.userRepository.findByCpfAccount(cpfAccount);

    if (user == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
    }

    if (!user.getNumberAccount().equals(numberAccount) || !user.getPassword().equals(password)) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas!");
    }
    
    // Login bem-sucedido
    return ResponseEntity.status(HttpStatus.OK).body("Login bem-sucedido!");

}


// Rota para obter informações da conta através do CPF
@PostMapping("/getId")
public ResponseEntity<?> getAccountInfoByCpf(@RequestBody UserModel userModel) {
    var user = this.userRepository.findByCpfAccount(userModel.getCpfAccount());
    if (user == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
    }


        UserResponseDTO userResponse = new UserResponseDTO(
                user.getNumberAccount(),
                user.getFirstName(),
                user.getLastName(),
                user.getCpfAccount(),
                user.getBalance(),
                user.getPassword()
        );

        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    // Rota para atualizar informações do usuário
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserResponseDTO updatedUser) {

        var existingUser = this.userRepository.findByCpfAccount(updatedUser.getCpfAccount());

        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }

        // Atualiza as informações do usuário
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setBalance(updatedUser.getBalance());
        existingUser.setPassword(updatedUser.getPassword());
        
        var userUpdated = this.userRepository.save(existingUser);

        return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
    }

    // Rota para deletar um usuário pelo CPF
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam String cpfAccount) {
        var user = this.userRepository.findByCpfAccount(cpfAccount);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }

        // Deleta o usuário
        this.userRepository.delete(user);

        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso!");
    }
}
