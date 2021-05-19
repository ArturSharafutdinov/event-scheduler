package ru.ivanov.evgeny.eventscheduler.services.auth;

import org.springframework.security.core.userdetails.UserDetails;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.AccountDto;

public interface AccountService {


    void submit(AccountDto accountDto);

    Account getAccountById(Long id);

    UserDetails createUserDetails(Account account);

    Account findByEmail(String email);

    Long register(AccountDto accountDto);

    boolean checkAccountForExistsByEmail(String email);
}

