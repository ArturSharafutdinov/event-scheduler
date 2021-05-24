package ru.ivanov.evgeny.eventscheduler.services.mappers;

import org.springframework.stereotype.Component;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.AccountDto;

@Component
public class AccountMapper {

    public Account mapToEntity(AccountDto accountDto) {
        Account account = new Account();

        account.setUsername(accountDto.getUsername());
        account.setAge(accountDto.getAge());
        account.setGender(accountDto.getGender());
        account.setCity(accountDto.getCity());

        return account;
    }

    public AccountDto mapToDto(Account account) {
        AccountDto accountDto = new AccountDto();

        accountDto.setId(account.getId());
        accountDto.setUsername(account.getUsername());
        accountDto.setAge(account.getAge());
        accountDto.setGender(account.getGender());
        accountDto.setCity(account.getCity());

        return accountDto;
    }
}
