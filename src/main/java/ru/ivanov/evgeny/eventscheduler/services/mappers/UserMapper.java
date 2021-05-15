package ru.ivanov.evgeny.eventscheduler.services.mappers;

import org.springframework.stereotype.Component;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.AccountDto;

@Component
public class UserMapper {

    public Account mapToEntity(AccountDto accountDto) {
        Account user = new Account();

        user.setUsername(
                accountDto.getUsername()
        );
        user.setPassword(
                accountDto.getPassword()
        );
        user.setEmail(
                accountDto.getEmail()
        );
        user.setFirstName(
                accountDto.getFirstName()
        );

        return user;
    }

    public AccountDto mapToDto(Account user) {
        AccountDto accountDto = new AccountDto();

        accountDto.setId(
                user.getId()
        );
        accountDto.setUsername(
                user.getUsername()
        );
        accountDto.setPassword(
                user.getPassword()
        );
        accountDto.setEmail(
                user.getEmail()
        );
        accountDto.setFirstName(
                user.getFirstName()
        );


        return accountDto;
    }
}
