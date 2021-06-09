package ru.ivanov.evgeny.eventscheduler.services.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.AccountDto;

@Component
public class AccountMapper {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    public Account mapToEntity(AccountDto accountDto) {
        Account account = new Account();

        account.setUsername(accountDto.getUsername());
        account.setPassword(accountDto.getPassword());
        account.setEmail(accountDto.getEmail());
        account.setFirstName(accountDto.getFirstName());
        account.setLastName(accountDto.getLastName());
        account.setAge(accountDto.getAge());
        account.setCity(accountDto.getCity());
        account.setGender(accountDto.getGender());

        return account;
    }

    public AccountDto mapToDto(Account account) {

        AccountDto accountDto = new AccountDto();

        accountDto.setId(account.getId());
        accountDto.setUsername(account.getUsername());
        accountDto.setEmail(account.getEmail());
        accountDto.setFirstName(account.getFirstName());
        accountDto.setLastName(account.getLastName());
        accountDto.setAge(account.getAge());
        accountDto.setCity(accountDto.getCity());
        accountDto.setGender(account.getGender());
        if (account.getImageInfo() != null) accountDto.setFileInfo(fileInfoMapper.mapToDto(account.getImageInfo()));

        return accountDto;
    }
}
