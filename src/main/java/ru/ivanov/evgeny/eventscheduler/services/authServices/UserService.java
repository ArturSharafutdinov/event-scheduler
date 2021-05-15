package ru.ivanov.evgeny.eventscheduler.services.authServices;

import org.springframework.security.core.userdetails.UserDetails;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.AccountDto;

public interface UserService {


    void submit(AccountDto accountDto);

    AccountDto getUserById(Long id);

    UserDetails createUserDetails(Account user);

    Account findByEmail(String email);

    Long register(AccountDto accountDto);

    boolean checkUserForExistsByEmail(String email);
}

