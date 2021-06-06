package ru.ivanov.evgeny.eventscheduler.services.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.AccountDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.AuthAccountDto;

import java.util.HashMap;

public interface AccountService {

    Account submit(AccountDto accountDto);

    Account getAccountById(Long id);

    UserDetails createUserDetails(Account account);

    Account findAccountByEmail(String email);

    Long register(AccountDto accountDto);

    boolean checkAccountForExistsByEmail(String email);

    HashMap<String, Object> login(AuthAccountDto authAccountDto);

    Account findAccount(Authentication authentication);
}

