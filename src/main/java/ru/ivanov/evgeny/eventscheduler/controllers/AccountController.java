package ru.ivanov.evgeny.eventscheduler.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.AccountRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.AccountDto;
import ru.ivanov.evgeny.eventscheduler.persistence.enums.Role;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AccountController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/signUp")
    public void registration(@RequestBody AccountDto accountDto) {
        Account user = accountRepository.findByEmail(accountDto.getEmail());
        if (user == null) {
            user = new Account();
            user.setUsername(
                    accountDto.getUsername()
            );
            user.setPassword(
                    passwordEncoder.encode(accountDto.getPassword())
            );
            user.setEmail(
                    accountDto.getEmail()
            );
            user.setFirstName(
                    accountDto.getFirstName()
            );
            user.setRole(
                    Role.USER
            );
            // TODO убрать после подтверждения email
            user.setEnabled(true);
            accountRepository.save(user);

        } else {
            throw new RuntimeException("User with this email exists");
        }


    }

}
